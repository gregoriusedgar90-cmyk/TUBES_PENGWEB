package models;

import java.util.ArrayList;

/**
 * INHERITANCE:
 * Class Penjual (Event Organizer) mewarisi dari abstract class User
 * 
 * POLYMORPHISM:
 * Override method abstract dari parent class
 */
public class Penjual extends User {
    
    // ENCAPSULATION: Private attributes khusus Penjual
    private String namaOrganisasi;
    private String nomorTelepon;
    private double totalPendapatan;
    private ArrayList<String> eventIds; // Menyimpan ID event yang dimiliki

    // Constructor
    public Penjual(String userId, String username, String password, String email, 
                   String namaOrganisasi, String nomorTelepon) {
        super(userId, username, password, email, "Penjual");
        this.namaOrganisasi = namaOrganisasi;
        this.nomorTelepon = nomorTelepon;
        this.totalPendapatan = 0.0;
        this.eventIds = new ArrayList<>();
    }

    // POLYMORPHISM: Override method abstract dari User
    @Override
    public void displayMenu() {
        System.out.println("\n========== MENU PENJUAL (EVENT ORGANIZER) ==========");
        System.out.println("1. Tambah Event Baru");
        System.out.println("2. Lihat Event Saya");
        System.out.println("3. Edit Event");
        System.out.println("4. Hapus Event");
        System.out.println("5. Lihat Statistik Penjualan");
        System.out.println("6. Logout");
        System.out.println("====================================================");
    }
    
    @Override
    public String getMenuOptions() {
        return "1-6";
    }

    // Method khusus Penjual
    public void tambahEventId(String eventId) {
        if (!eventIds.contains(eventId)) {
            eventIds.add(eventId);
        }
    }

    public void hapusEventId(String eventId) {
        eventIds.remove(eventId);
    }

    public void updatePendapatan(double jumlah) {
        this.totalPendapatan += jumlah;
    }

    public void lihatStatistikPenjualan(int totalEventDibuat, int totalTiketTerjual) {
        System.out.println("\n=== STATISTIK PENJUALAN ===");
        System.out.println("Organisasi: " + namaOrganisasi);
        System.out.println("Total Event Dibuat: " + totalEventDibuat);
        System.out.println("Total Tiket Terjual: " + totalTiketTerjual);
        System.out.println("Total Pendapatan: Rp " + String.format("%,.2f", totalPendapatan));
    }

    // ENCAPSULATION: Getter dan Setter
    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public double getTotalPendapatan() {
        return totalPendapatan;
    }

    public ArrayList<String> getEventIds() {
        return new ArrayList<>(eventIds); // Return copy untuk enkapsulasi
    }

    @Override
    public String toString() {
        return "Penjual{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", namaOrganisasi='" + namaOrganisasi + '\'' +
                ", nomorTelepon='" + nomorTelepon + '\'' +
                ", totalPendapatan=" + totalPendapatan +
                ", jumlahEvent=" + eventIds.size() +
                ", role='" + getRole() + '\'' +
                '}';
    }
}

