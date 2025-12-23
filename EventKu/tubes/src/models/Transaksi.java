package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ENCAPSULATION:
 * Class Transaksi untuk merekam setiap pembelian tiket
 * Menyimpan informasi lengkap tentang transaksi
 */
public class Transaksi {
    // ENCAPSULATION: Private attributes
    private String transaksiId;
    private String pembeliId;
    private String eventId;
    private int jumlahTiket;
    private double totalHarga;
    private String statusPembayaran; // "Pending", "Paid", "Cancelled"
    private String metodePembayaran; // "Transfer Bank", "E-Wallet", "Kartu Kredit"
    private String kodeVerifikasi;
    private LocalDateTime tanggalTransaksi;
    private LocalDateTime tanggalPembayaran;

    // Constructor
    public Transaksi(String transaksiId, String pembeliId, String eventId, 
                     int jumlahTiket, double totalHarga) {
        this.transaksiId = transaksiId;
        this.pembeliId = pembeliId;
        this.eventId = eventId;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = totalHarga;
        this.statusPembayaran = "Pending";
        this.tanggalTransaksi = LocalDateTime.now();
        this.kodeVerifikasi = null;
        this.tanggalPembayaran = null;
    }

    // Method untuk konfirmasi pembayaran
    public void konfirmasiPembayaran(String metodePembayaran, String kodeVerifikasi) {
        this.statusPembayaran = "Paid";
        this.metodePembayaran = metodePembayaran;
        this.kodeVerifikasi = kodeVerifikasi;
        this.tanggalPembayaran = LocalDateTime.now();
    }

    // Method untuk membatalkan transaksi
    public void batalkanTransaksi() {
        this.statusPembayaran = "Cancelled";
    }

    // Method untuk cek apakah transaksi sudah dibayar
    public boolean isPaid() {
        return statusPembayaran.equals("Paid");
    }

    // ENCAPSULATION: Getter methods
    public String getTransaksiId() {
        return transaksiId;
    }

    public String getPembeliId() {
        return pembeliId;
    }

    public String getEventId() {
        return eventId;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public String getKodeVerifikasi() {
        return kodeVerifikasi;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    // ENCAPSULATION: Setter methods (terbatas untuk keamanan)
    public void setStatusPembayaran(String statusPembayaran) {
        if (statusPembayaran.equals("Pending") || 
            statusPembayaran.equals("Paid") || 
            statusPembayaran.equals("Cancelled")) {
            this.statusPembayaran = statusPembayaran;
        }
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== DETAIL TRANSAKSI ===");
        sb.append("\nTransaksi ID: ").append(transaksiId);
        sb.append("\nPembeli ID: ").append(pembeliId);
        sb.append("\nEvent ID: ").append(eventId);
        sb.append("\nJumlah Tiket: ").append(jumlahTiket);
        sb.append("\nTotal Harga: Rp ").append(String.format("%,.2f", totalHarga));
        sb.append("\nStatus: ").append(statusPembayaran);
        sb.append("\nTanggal Transaksi: ").append(tanggalTransaksi.format(formatter));
        
        if (isPaid()) {
            sb.append("\nMetode Pembayaran: ").append(metodePembayaran);
            sb.append("\nKode Verifikasi: ").append(kodeVerifikasi);
            sb.append("\nTanggal Pembayaran: ").append(tanggalPembayaran.format(formatter));
        }
        
        sb.append("\n========================");
        return sb.toString();
    }

    // Method untuk display ringkas
    public String toStringRingkas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("%-20s | %-15s | %2d tiket | Rp %-12s | %-10s | %s",
                transaksiId,
                eventId,
                jumlahTiket,
                String.format("%,.0f", totalHarga),
                statusPembayaran,
                tanggalTransaksi.format(formatter));
    }
}

