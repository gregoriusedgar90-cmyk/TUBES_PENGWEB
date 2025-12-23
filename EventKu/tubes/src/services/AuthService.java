package services;

import models.*;
import exceptions.LoginGagalException;
import java.util.ArrayList;

/**
 * SERVICE LAYER:
 * AuthService menangani proses authentication (Login & Register)
 * Menerapkan prinsip separation of concerns
 */
public class AuthService {
    private Database database;
    private User currentUser; // User yang sedang login

    public AuthService() {
        this.database = Database.getInstance();
        this.currentUser = null;
    }

    /**
     * EXCEPTION HANDLING:
     * Method login dengan exception handling
     * Throws LoginGagalException jika kredensial salah
     */
    public User login(String username, String password) throws LoginGagalException {
        try {
            // Validasi input
            if (username == null || username.isEmpty()) {
                throw new LoginGagalException("Username tidak boleh kosong!");
            }
            if (password == null || password.isEmpty()) {
                throw new LoginGagalException("Password tidak boleh kosong!");
            }

            // Cari user berdasarkan username
            User user = database.getUserByUsername(username);
            
            if (user == null) {
                throw new LoginGagalException("Username tidak ditemukan!");
            }

            // Validasi password
            if (!user.getPassword().equals(password)) {
                throw new LoginGagalException("Password salah!");
            }

            // Cek apakah akun aktif
            if (!user.isActive()) {
                throw new LoginGagalException("Akun Anda telah dinonaktifkan!");
            }

            // Login berhasil
            currentUser = user;
            System.out.println("\n✓ Login berhasil! Selamat datang, " + user.getUsername());
            return user;

        } catch (LoginGagalException e) {
            System.out.println("\n✗ Login gagal: " + e.getMessage());
            throw e; // Re-throw untuk handling di level atas
        }
    }

    /**
     * Method untuk registrasi user baru
     * Mengembalikan true jika berhasil, false jika gagal
     */
    public boolean register(String username, String password, String email, 
                           String role, String... additionalInfo) {
        try {
            // Validasi input
            if (username == null || username.isEmpty()) {
                System.out.println("✗ Username tidak boleh kosong!");
                return false;
            }
            if (password == null || password.length() < 6) {
                System.out.println("✗ Password minimal 6 karakter!");
                return false;
            }
            if (email == null || !email.contains("@")) {
                System.out.println("✗ Email tidak valid!");
                return false;
            }

            // Cek apakah username sudah ada
            if (database.getUserByUsername(username) != null) {
                System.out.println("✗ Username sudah digunakan!");
                return false;
            }

            // Generate user ID
            String userId = database.generateNewUserId(role);

            // Buat user baru berdasarkan role
            User newUser = null;
            switch (role) {
                case "Admin":
                    String adminLevel = additionalInfo.length > 0 ? additionalInfo[0] : "Moderator";
                    newUser = new Admin(userId, username, password, email, adminLevel);
                    break;
                    
                case "Penjual":
                    String namaOrganisasi = additionalInfo.length > 0 ? additionalInfo[0] : "Organisasi";
                    String nomorTelepon = additionalInfo.length > 1 ? additionalInfo[1] : "-";
                    newUser = new Penjual(userId, username, password, email, namaOrganisasi, nomorTelepon);
                    break;
                    
                case "Pembeli":
                    String alamat = additionalInfo.length > 0 ? additionalInfo[0] : "-";
                    String telepon = additionalInfo.length > 1 ? additionalInfo[1] : "-";
                    newUser = new Pembeli(userId, username, password, email, alamat, telepon);
                    break;
                    
                default:
                    System.out.println("✗ Role tidak valid!");
                    return false;
            }

            // Simpan ke database
            database.addUser(newUser);
            System.out.println("\n✓ Registrasi berhasil!");
            System.out.println("User ID Anda: " + userId);
            System.out.println("Silakan login dengan username dan password Anda.");
            return true;

        } catch (Exception e) {
            System.out.println("✗ Registrasi gagal: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method untuk logout
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("\n✓ Logout berhasil. Sampai jumpa, " + currentUser.getUsername() + "!");
            currentUser = null;
        }
    }

    /**
     * Method untuk cek apakah ada user yang sedang login
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Method untuk mendapatkan current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Method untuk validasi role user
     */
    public boolean hasRole(String role) {
        if (currentUser == null) return false;
        return currentUser.getRole().equals(role);
    }

    /**
     * Method untuk change password
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (currentUser == null) {
            System.out.println("✗ Anda belum login!");
            return false;
        }

        if (!currentUser.getPassword().equals(oldPassword)) {
            System.out.println("✗ Password lama salah!");
            return false;
        }

        if (newPassword.length() < 6) {
            System.out.println("✗ Password baru minimal 6 karakter!");
            return false;
        }

        currentUser.setPassword(newPassword);
        System.out.println("✓ Password berhasil diubah!");
        return true;
    }
}

