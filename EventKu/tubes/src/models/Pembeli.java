package models;

import interfaces.Pembayaran;
import exceptions.PembayaranGagalException;
import java.util.ArrayList;
import java.util.Random;

/**
 * INHERITANCE:
 * Class Pembeli mewarisi dari abstract class User
 * 
 * INTERFACE IMPLEMENTATION:
 * Mengimplementasikan interface Pembayaran untuk proses transaksi
 * 
 * POLYMORPHISM:
 * Override method abstract dari parent class
 */
public class Pembeli extends User implements Pembayaran {
    
    // ENCAPSULATION: Private attributes khusus Pembeli
    private String alamat;
    private String nomorTelepon;
    private ArrayList<String> riwayatTransaksiIds;

    // Constructor
    public Pembeli(String userId, String username, String password, String email, 
                   String alamat, String nomorTelepon) {
        super(userId, username, password, email, "Pembeli");
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.riwayatTransaksiIds = new ArrayList<>();
    }

    // POLYMORPHISM: Override method abstract dari User
    @Override
    public void displayMenu() {
        System.out.println("\n========== MENU PEMBELI ==========");
        System.out.println("1. Browse Event");
        System.out.println("2. Cari Event");
        System.out.println("3. Pesan Tiket");
        System.out.println("4. Riwayat Transaksi");
        System.out.println("5. Lihat Tiket Saya");
        System.out.println("6. Logout");
        System.out.println("==================================");
    }
    
    @Override
    public String getMenuOptions() {
        return "1-6";
    }

    // INTERFACE IMPLEMENTATION: Implementasi method dari interface Pembayaran
    @Override
    public boolean prosesPembayaran(String transaksiId, String metodePembayaran) {
        System.out.println("\n=== PROSES PEMBAYARAN ===");
        System.out.println("Transaksi ID: " + transaksiId);
        System.out.println("Metode Pembayaran: " + metodePembayaran);
        System.out.println("Memproses pembayaran...");
        
        // Simulasi proses pembayaran
        try {
            Thread.sleep(1000); // Simulasi delay
            System.out.println("Pembayaran berhasil!");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Pembayaran gagal!");
            return false;
        }
    }

    @Override
    public double hitungTotal(int jumlahTiket, double hargaTiket) {
        return jumlahTiket * hargaTiket;
    }

    @Override
    public String generateKodePembayaran() {
        // Generate kode pembayaran unik
        Random random = new Random();
        return "PAY" + System.currentTimeMillis() + random.nextInt(1000);
    }

    @Override
    public boolean validasiPembayaran(String kodeVerifikasi) {
        // Simulasi validasi kode pembayaran
        return kodeVerifikasi != null && kodeVerifikasi.startsWith("PAY");
    }

    // Method khusus Pembeli
    public void tambahRiwayatTransaksi(String transaksiId) {
        riwayatTransaksiIds.add(transaksiId);
    }

    public void lihatRiwayatTransaksi(ArrayList<Object> transaksiList) {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (riwayatTransaksiIds.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (String transaksiId : riwayatTransaksiIds) {
                System.out.println("- Transaksi ID: " + transaksiId);
            }
        }
    }

    // ENCAPSULATION: Getter dan Setter
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public ArrayList<String> getRiwayatTransaksiIds() {
        return new ArrayList<>(riwayatTransaksiIds); // Return copy untuk enkapsulasi
    }

    @Override
    public String toString() {
        return "Pembeli{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", alamat='" + alamat + '\'' +
                ", nomorTelepon='" + nomorTelepon + '\'' +
                ", jumlahTransaksi=" + riwayatTransaksiIds.size() +
                ", role='" + getRole() + '\'' +
                '}';
    }
}

