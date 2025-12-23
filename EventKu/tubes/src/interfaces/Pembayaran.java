package interfaces;

/**
 * INTERFACE:
 * Interface Pembayaran untuk proses transaksi
 * Menerapkan prinsip polymorphism dan abstraksi
 */
public interface Pembayaran {
    boolean prosesPembayaran(String transaksiId, String metodePembayaran);
    double hitungTotal(int jumlahTiket, double hargaTiket);
    String generateKodePembayaran();
    boolean validasiPembayaran(String kodeVerifikasi);
}

