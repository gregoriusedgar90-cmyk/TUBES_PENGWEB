package services;

import models.*;
import exceptions.*;
import java.util.ArrayList;

/**
 * SERVICE LAYER:
 * TransaksiService menangani semua operasi terkait Transaksi dan Pembelian Tiket
 * Menerapkan business logic dengan exception handling
 */
public class TransaksiService {
    private Database database;

    public TransaksiService() {
        this.database = Database.getInstance();
    }

    /**
     * EXCEPTION HANDLING:
     * Method untuk membuat transaksi baru dengan exception handling
     * Throws TiketHabisException jika kuota tidak mencukupi
     */
    public Transaksi createTransaksi(String pembeliId, String eventId, int jumlahTiket)
            throws TiketHabisException, EventTidakDitemukanException {
        
        // Validasi event
        Event event = database.getEventById(eventId);
        if (event == null) {
            throw new EventTidakDitemukanException("Event dengan ID " + eventId + " tidak ditemukan!");
        }

        // Validasi status event
        if (!event.isVerified()) {
            throw new EventTidakDitemukanException("Event belum diverifikasi atau ditolak!");
        }

        // Validasi kuota tiket
        if (jumlahTiket <= 0) {
            System.out.println("✗ Jumlah tiket harus lebih dari 0!");
            return null;
        }

        if (event.getKuotaTersisa() < jumlahTiket) {
            throw new TiketHabisException(event.getNamaEvent(), jumlahTiket, event.getKuotaTersisa());
        }

        // Hitung total harga
        double totalHarga = jumlahTiket * event.getHargaTiket();

        // Generate transaksi ID
        String transaksiId = database.generateNewTransaksiId();

        // Buat transaksi baru (status: Pending)
        Transaksi transaksi = new Transaksi(transaksiId, pembeliId, eventId, jumlahTiket, totalHarga);

        // Kurangi kuota tiket (reserved untuk transaksi ini)
        event.kurangiKuota(jumlahTiket);

        // Simpan transaksi ke database
        database.addTransaksi(transaksi);

        System.out.println("\n✓ Transaksi berhasil dibuat!");
        System.out.println("Transaksi ID: " + transaksiId);
        System.out.println("Total yang harus dibayar: Rp " + String.format("%,.2f", totalHarga));
        System.out.println("\nSilakan lanjutkan ke pembayaran...");

        return transaksi;
    }

    /**
     * EXCEPTION HANDLING:
     * Method untuk proses pembayaran dengan exception handling
     * Throws PembayaranGagalException jika pembayaran gagal
     */
    public boolean prosesPembayaran(String transaksiId, String metodePembayaran, String kodeVerifikasi)
            throws PembayaranGagalException {
        
        try {
            // Validasi transaksi
            Transaksi transaksi = database.getTransaksiById(transaksiId);
            if (transaksi == null) {
                throw new PembayaranGagalException("Transaksi tidak ditemukan!");
            }

            // Cek apakah transaksi sudah dibayar
            if (transaksi.isPaid()) {
                throw new PembayaranGagalException("Transaksi sudah dibayar sebelumnya!");
            }

            // Validasi kode verifikasi (simulasi)
            if (kodeVerifikasi == null || kodeVerifikasi.isEmpty()) {
                throw new PembayaranGagalException("Kode verifikasi tidak valid!");
            }

            // Simulasi proses pembayaran
            System.out.println("\n=== MEMPROSES PEMBAYARAN ===");
            System.out.println("Transaksi ID: " + transaksiId);
            System.out.println("Metode: " + metodePembayaran);
            System.out.println("Memvalidasi pembayaran...");

            try {
                Thread.sleep(1500); // Simulasi delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Konfirmasi pembayaran
            transaksi.konfirmasiPembayaran(metodePembayaran, kodeVerifikasi);

            // Generate tiket virtual
            generateTiket(transaksi);

            // Update pendapatan penjual
            updatePendapatanPenjual(transaksi);

            // Update riwayat pembeli
            updateRiwayatPembeli(transaksi);

            System.out.println("\n✓✓✓ PEMBAYARAN BERHASIL! ✓✓✓");
            System.out.println("Tiket virtual Anda telah digenerate.");
            
            return true;

        } catch (PembayaranGagalException e) {
            System.out.println("✗ " + e.getMessage());
            throw e;
        }
    }

    /**
     * Method untuk generate tiket virtual setelah pembayaran berhasil
     */
    private void generateTiket(Transaksi transaksi) {
        Event event = database.getEventById(transaksi.getEventId());
        User pembeli = database.getUserById(transaksi.getPembeliId());

        // Generate tiket sebanyak jumlah yang dibeli
        for (int i = 0; i < transaksi.getJumlahTiket(); i++) {
            String tiketId = database.generateNewTiketId();
            
            Tiket tiket = new Tiket(
                tiketId,
                transaksi.getTransaksiId(),
                event.getEventId(),
                pembeli.getUserId(),
                event.getNamaEvent(),
                pembeli.getUsername(),
                event.getTanggalEvent(),
                event.getLokasi()
            );

            database.addTiket(tiket);
        }

        System.out.println("✓ " + transaksi.getJumlahTiket() + " tiket berhasil digenerate!");
    }

    /**
     * Method untuk update pendapatan penjual
     */
    private void updatePendapatanPenjual(Transaksi transaksi) {
        Event event = database.getEventById(transaksi.getEventId());
        User penjual = database.getUserById(event.getPenjualId());

        if (penjual instanceof Penjual) {
            ((Penjual) penjual).updatePendapatan(transaksi.getTotalHarga());
        }
    }

    /**
     * Method untuk update riwayat pembeli
     */
    private void updateRiwayatPembeli(Transaksi transaksi) {
        User pembeli = database.getUserById(transaksi.getPembeliId());

        if (pembeli instanceof Pembeli) {
            ((Pembeli) pembeli).tambahRiwayatTransaksi(transaksi.getTransaksiId());
        }
    }

    /**
     * Method untuk membatalkan transaksi
     */
    public boolean batalkanTransaksi(String transaksiId, String userId) {
        try {
            Transaksi transaksi = database.getTransaksiById(transaksiId);
            
            if (transaksi == null) {
                System.out.println("✗ Transaksi tidak ditemukan!");
                return false;
            }

            // Validasi ownership
            if (!transaksi.getPembeliId().equals(userId)) {
                System.out.println("✗ Anda tidak memiliki akses untuk membatalkan transaksi ini!");
                return false;
            }

            // Cek apakah transaksi sudah dibayar
            if (transaksi.isPaid()) {
                System.out.println("✗ Transaksi yang sudah dibayar tidak dapat dibatalkan!");
                System.out.println("Silakan hubungi customer service untuk refund.");
                return false;
            }

            // Kembalikan kuota tiket
            Event event = database.getEventById(transaksi.getEventId());
            if (event != null) {
                event.kembalikanKuota(transaksi.getJumlahTiket());
            }

            // Batalkan transaksi
            transaksi.batalkanTransaksi();

            System.out.println("✓ Transaksi berhasil dibatalkan!");
            System.out.println("Kuota tiket telah dikembalikan.");
            
            return true;

        } catch (Exception e) {
            System.out.println("✗ Gagal membatalkan transaksi: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method untuk mendapatkan riwayat transaksi pembeli
     */
    public ArrayList<Transaksi> getRiwayatTransaksi(String pembeliId) {
        return database.getTransaksisByPembeli(pembeliId);
    }

    /**
     * Method untuk mendapatkan tiket pembeli
     */
    public ArrayList<Tiket> getTiketPembeli(String pembeliId) {
        return database.getTiketsByPembeli(pembeliId);
    }

    /**
     * Method untuk menampilkan tiket virtual
     */
    public void displayTiketVirtual(String tiketId) {
        Tiket tiket = database.getTiketById(tiketId);
        if (tiket != null) {
            tiket.displayTiketVirtual();
        } else {
            System.out.println("✗ Tiket tidak ditemukan!");
        }
    }

    /**
     * Method untuk mendapatkan semua transaksi (untuk Admin)
     */
    public ArrayList<Transaksi> getAllTransaksi() {
        return database.getAllTransaksis();
    }

    /**
     * Method untuk statistik penjualan event (untuk Penjual)
     */
    public void displayStatistikEvent(String eventId) {
        Event event = database.getEventById(eventId);
        if (event == null) {
            System.out.println("✗ Event tidak ditemukan!");
            return;
        }

        ArrayList<Transaksi> transaksis = database.getTransaksisByEvent(eventId);
        
        int totalTiketTerjual = 0;
        double totalPendapatan = 0.0;
        int transaksiPaid = 0;

        for (Transaksi transaksi : transaksis) {
            if (transaksi.isPaid()) {
                totalTiketTerjual += transaksi.getJumlahTiket();
                totalPendapatan += transaksi.getTotalHarga();
                transaksiPaid++;
            }
        }

        System.out.println("\n=== STATISTIK EVENT ===");
        System.out.println("Nama Event: " + event.getNamaEvent());
        System.out.println("Total Tiket Terjual: " + totalTiketTerjual + "/" + event.getKuotaTotal());
        System.out.println("Tiket Tersisa: " + event.getKuotaTersisa());
        System.out.println("Total Transaksi Berhasil: " + transaksiPaid);
        System.out.println("Total Pendapatan: Rp " + String.format("%,.2f", totalPendapatan));
        System.out.println("======================");
    }
}

