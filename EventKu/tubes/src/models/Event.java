package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ENCAPSULATION:
 * Class Event dengan private attributes dan public getter/setter
 * Menyimpan informasi lengkap tentang event
 */
public class Event {
    // ENCAPSULATION: Private attributes
    private String eventId;
    private String namaEvent;
    private String deskripsi;
    private double hargaTiket;
    private int kuotaTotal;
    private int kuotaTersisa;
    private String lokasi;
    private LocalDateTime tanggalEvent;
    private String kategori; // "Konser", "Seminar", "Workshop", "Olahraga", dll
    private String posterUrl; // URL atau path ke poster
    private String penjualId; // ID dari penjual/organizer
    private String statusVerifikasi; // "Pending", "Approved", "Rejected"
    private LocalDateTime tanggalDibuat;

    // Constructor
    public Event(String eventId, String namaEvent, String deskripsi, double hargaTiket,
                 int kuotaTotal, String lokasi, LocalDateTime tanggalEvent, 
                 String kategori, String posterUrl, String penjualId) {
        this.eventId = eventId;
        this.namaEvent = namaEvent;
        this.deskripsi = deskripsi;
        this.hargaTiket = hargaTiket;
        this.kuotaTotal = kuotaTotal;
        this.kuotaTersisa = kuotaTotal; // Awalnya sama dengan total
        this.lokasi = lokasi;
        this.tanggalEvent = tanggalEvent;
        this.kategori = kategori;
        this.posterUrl = posterUrl;
        this.penjualId = penjualId;
        this.statusVerifikasi = "Pending"; // Default pending
        this.tanggalDibuat = LocalDateTime.now();
    }

    // Method untuk mengurangi kuota tiket (dipanggil saat pembelian)
    public boolean kurangiKuota(int jumlah) {
        if (kuotaTersisa >= jumlah) {
            kuotaTersisa -= jumlah;
            return true;
        }
        return false;
    }

    // Method untuk mengembalikan kuota tiket (jika transaksi dibatalkan)
    public void kembalikanKuota(int jumlah) {
        if (kuotaTersisa + jumlah <= kuotaTotal) {
            kuotaTersisa += jumlah;
        }
    }

    // Method untuk cek apakah event sudah penuh
    public boolean isFull() {
        return kuotaTersisa == 0;
    }

    // Method untuk cek apakah event sudah diverifikasi
    public boolean isVerified() {
        return statusVerifikasi.equals("Approved");
    }

    // ENCAPSULATION: Getter methods
    public String getEventId() {
        return eventId;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public int getKuotaTotal() {
        return kuotaTotal;
    }

    public int getKuotaTersisa() {
        return kuotaTersisa;
    }

    public String getLokasi() {
        return lokasi;
    }

    public LocalDateTime getTanggalEvent() {
        return tanggalEvent;
    }

    public String getKategori() {
        return kategori;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getPenjualId() {
        return penjualId;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public LocalDateTime getTanggalDibuat() {
        return tanggalDibuat;
    }

    // ENCAPSULATION: Setter methods dengan validasi
    public void setNamaEvent(String namaEvent) {
        if (namaEvent != null && !namaEvent.isEmpty()) {
            this.namaEvent = namaEvent;
        }
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setHargaTiket(double hargaTiket) {
        if (hargaTiket >= 0) {
            this.hargaTiket = hargaTiket;
        }
    }

    public void setKuotaTotal(int kuotaTotal) {
        if (kuotaTotal > 0) {
            this.kuotaTotal = kuotaTotal;
        }
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setTanggalEvent(LocalDateTime tanggalEvent) {
        this.tanggalEvent = tanggalEvent;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        if (statusVerifikasi.equals("Pending") || 
            statusVerifikasi.equals("Approved") || 
            statusVerifikasi.equals("Rejected")) {
            this.statusVerifikasi = statusVerifikasi;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "\n=== EVENT DETAIL ===" +
                "\nEvent ID: " + eventId +
                "\nNama Event: " + namaEvent +
                "\nDeskripsi: " + deskripsi +
                "\nHarga Tiket: Rp " + String.format("%,.2f", hargaTiket) +
                "\nKuota: " + kuotaTersisa + "/" + kuotaTotal +
                "\nLokasi: " + lokasi +
                "\nTanggal Event: " + tanggalEvent.format(formatter) +
                "\nKategori: " + kategori +
                "\nStatus: " + statusVerifikasi +
                "\n==================";
    }

    // Method untuk display ringkas (untuk list)
    public String toStringRingkas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("%-15s | %-30s | Rp %-12s | %3d tiket | %s | %s",
                eventId, 
                namaEvent.length() > 30 ? namaEvent.substring(0, 27) + "..." : namaEvent,
                String.format("%,.0f", hargaTiket),
                kuotaTersisa,
                tanggalEvent.format(formatter),
                statusVerifikasi);
    }
}

