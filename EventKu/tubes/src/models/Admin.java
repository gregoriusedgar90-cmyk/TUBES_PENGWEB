package models;

import interfaces.Verifikasi;
import java.util.ArrayList;

/**
 * INHERITANCE:
 * Class Admin mewarisi dari abstract class User
 * 
 * INTERFACE IMPLEMENTATION:
 * Mengimplementasikan interface Verifikasi untuk verifikasi event
 * 
 * POLYMORPHISM:
 * Override method abstract dari parent class
 */
public class Admin extends User implements Verifikasi {
    
    // ENCAPSULATION: Private attributes khusus Admin
    private String adminLevel; // "Super Admin", "Moderator"
    
    // Constructor
    public Admin(String userId, String username, String password, String email, String adminLevel) {
        super(userId, username, password, email, "Admin");
        this.adminLevel = adminLevel;
    }

    // POLYMORPHISM: Override method abstract dari User
    @Override
    public void displayMenu() {
        System.out.println("\n========== MENU ADMIN ==========");
        System.out.println("1. Verifikasi Event Pending");
        System.out.println("2. Kelola User (View/Edit/Delete)");
        System.out.println("3. Lihat Laporan Transaksi");
        System.out.println("4. Lihat Statistik Sistem");
        System.out.println("5. Logout");
        System.out.println("================================");
    }
    
    @Override
    public String getMenuOptions() {
        return "1-5";
    }

    // INTERFACE IMPLEMENTATION: Implementasi method dari interface Verifikasi
    @Override
    public boolean verifikasi(String eventId) {
        System.out.println("[Admin] Memverifikasi event dengan ID: " + eventId);
        // Logika verifikasi akan diimplementasikan di service layer
        return true;
    }

    @Override
    public void approveVerifikasi(String eventId) {
        System.out.println("[Admin] Event " + eventId + " telah disetujui!");
    }

    @Override
    public void rejectVerifikasi(String eventId) {
        System.out.println("[Admin] Event " + eventId + " ditolak!");
    }

    @Override
    public String getStatusVerifikasi(String eventId) {
        return "Status verifikasi untuk event: " + eventId;
    }

    // Method khusus Admin
    public void lihatLaporanTransaksi(ArrayList<Object> transaksiList) {
        System.out.println("\n=== LAPORAN TRANSAKSI ===");
        if (transaksiList.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Object transaksi : transaksiList) {
                System.out.println(transaksi.toString());
            }
        }
    }

    public void lihatStatistikSistem(int totalUser, int totalEvent, int totalTransaksi) {
        System.out.println("\n=== STATISTIK SISTEM ===");
        System.out.println("Total User: " + totalUser);
        System.out.println("Total Event: " + totalEvent);
        System.out.println("Total Transaksi: " + totalTransaksi);
    }

    // ENCAPSULATION: Getter dan Setter
    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", adminLevel='" + adminLevel + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}

