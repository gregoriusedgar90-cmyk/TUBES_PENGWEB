package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * ENCAPSULATION:
 * Class Tiket untuk merepresentasikan tiket virtual yang digenerate
 * setelah pembayaran berhasil
 */
public class Tiket {
    // ENCAPSULATION: Private attributes
    private String tiketId;
    private String transaksiId;
    private String eventId;
    private String pembeliId;
    private String namaEvent;
    private String namaPembeli;
    private LocalDateTime tanggalEvent;
    private String lokasi;
    private String qrCode; // Simulasi QR Code (string unik)
    private String barcodeNumber;
    private LocalDateTime tanggalGenerate;
    private boolean isUsed; // Status apakah tiket sudah digunakan

    // Constructor
    public Tiket(String tiketId, String transaksiId, String eventId, String pembeliId,
                 String namaEvent, String namaPembeli, LocalDateTime tanggalEvent, String lokasi) {
        this.tiketId = tiketId;
        this.transaksiId = transaksiId;
        this.eventId = eventId;
        this.pembeliId = pembeliId;
        this.namaEvent = namaEvent;
        this.namaPembeli = namaPembeli;
        this.tanggalEvent = tanggalEvent;
        this.lokasi = lokasi;
        this.qrCode = generateQRCode();
        this.barcodeNumber = generateBarcode();
        this.tanggalGenerate = LocalDateTime.now();
        this.isUsed = false;
    }

    // Method untuk generate QR Code (simulasi)
    private String generateQRCode() {
        Random random = new Random();
        return "QR" + System.currentTimeMillis() + random.nextInt(10000);
    }

    // Method untuk generate Barcode (simulasi)
    private String generateBarcode() {
        Random random = new Random();
        long barcode = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(barcode);
    }

    // Method untuk menandai tiket sudah digunakan
    public void gunakan() {
        this.isUsed = true;
    }

    // Method untuk display tiket virtual
    public void displayTiketVirtual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm");
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              TIKET VIRTUAL - EVENT ONLINE                  ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Tiket ID    : " + String.format("%-41s", tiketId) + "║");
        System.out.println("║ Barcode     : " + String.format("%-41s", barcodeNumber) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Event       : " + String.format("%-41s", namaEvent.length() > 41 ? namaEvent.substring(0, 38) + "..." : namaEvent) + "║");
        System.out.println("║ Nama        : " + String.format("%-41s", namaPembeli) + "║");
        System.out.println("║ Tanggal     : " + String.format("%-41s", tanggalEvent.format(formatter)) + "║");
        System.out.println("║ Lokasi      : " + String.format("%-41s", lokasi.length() > 41 ? lokasi.substring(0, 38) + "..." : lokasi) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ QR Code     : " + String.format("%-41s", qrCode) + "║");
        System.out.println("║ Status      : " + String.format("%-41s", isUsed ? "SUDAH DIGUNAKAN" : "AKTIF") + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n** Simpan tiket ini dan tunjukkan saat masuk event **\n");
    }

    // ENCAPSULATION: Getter methods
    public String getTiketId() {
        return tiketId;
    }

    public String getTransaksiId() {
        return transaksiId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getPembeliId() {
        return pembeliId;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public LocalDateTime getTanggalEvent() {
        return tanggalEvent;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public LocalDateTime getTanggalGenerate() {
        return tanggalGenerate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Tiket{" +
                "tiketId='" + tiketId + '\'' +
                ", namaEvent='" + namaEvent + '\'' +
                ", namaPembeli='" + namaPembeli + '\'' +
                ", tanggalEvent=" + tanggalEvent.format(formatter) +
                ", status=" + (isUsed ? "Digunakan" : "Aktif") +
                '}';
    }
}

