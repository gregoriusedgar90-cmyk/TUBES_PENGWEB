package services;

import models.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * COLLECTION:
 * Class Database menggunakan ArrayList untuk simulasi database
 * Menyimpan semua data User, Event, Transaksi, dan Tiket
 * 
 * SINGLETON PATTERN (Bonus):
 * Menggunakan singleton pattern agar hanya ada satu instance database
 */
public class Database {
    // SINGLETON: Instance tunggal dari Database
    private static Database instance;
    
    // COLLECTION: ArrayList sebagai simulasi database
    private ArrayList<User> users;
    private ArrayList<Event> events;
    private ArrayList<Transaksi> transaksis;
    private ArrayList<Tiket> tikets;

    // Private constructor untuk Singleton Pattern
    private Database() {
        users = new ArrayList<>();
        events = new ArrayList<>();
        transaksis = new ArrayList<>();
        tikets = new ArrayList<>();
        
        // Initialize dengan data dummy
        initializeDummyData();
    }

    // SINGLETON: Method untuk mendapatkan instance
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Initialize data dummy untuk testing
    private void initializeDummyData() {
        // Tambah user dummy
        Admin admin = new Admin("ADM001", "admin", "admin123", "admin@eventku.com", "Super Admin");
        Penjual penjual1 = new Penjual("PNJ001", "organizer1", "organizer123", "organizer@event.com", 
                                        "Event Organizer Inc", "081234567890");
        Pembeli pembeli1 = new Pembeli("PMB001", "pembeli1", "pembeli123", "pembeli@email.com",
                                        "Jl. Sudirman No. 123", "081234567890");
        
        users.add(admin);
        users.add(penjual1);
        users.add(pembeli1);

        // Tambah event dummy
        Event event1 = new Event("EVT001", "Konser Musik Rock 2025", 
                                 "Konser musik rock terbesar tahun ini dengan band-band terkenal",
                                 150000.0, 500, "Stadion Utama Jakarta", 
                                 LocalDateTime.of(2025, 12, 31, 19, 0),
                                 "Konser", "poster1.jpg", "PNJ001");
        event1.setStatusVerifikasi("Approved");
        
        Event event2 = new Event("EVT002", "Workshop Digital Marketing",
                                 "Belajar strategi digital marketing dari para ahli",
                                 200000.0, 100, "Hotel Grand Indonesia",
                                 LocalDateTime.of(2025, 12, 25, 9, 0),
                                 "Workshop", "poster2.jpg", "PNJ001");
        event2.setStatusVerifikasi("Approved");
        
        Event event3 = new Event("EVT003", "Seminar Teknologi AI",
                                 "Seminar tentang perkembangan AI dan implementasinya",
                                 100000.0, 200, "Universitas Indonesia",
                                 LocalDateTime.of(2025, 12, 28, 13, 0),
                                 "Seminar", "poster3.jpg", "PNJ001");
        // Event ini masih pending
        
        events.add(event1);
        events.add(event2);
        events.add(event3);
    }

    // ===== USER MANAGEMENT =====
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users); // Return copy
    }

    public boolean deleteUser(String userId) {
        return users.removeIf(user -> user.getUserId().equals(userId));
    }

    public int getTotalUsers() {
        return users.size();
    }

    // ===== EVENT MANAGEMENT =====
    public void addEvent(Event event) {
        events.add(event);
    }

    public Event getEventById(String eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }

    public ArrayList<Event> getAllEvents() {
        return new ArrayList<>(events); // Return copy
    }

    public ArrayList<Event> getEventsByStatus(String status) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getStatusVerifikasi().equals(status)) {
                result.add(event);
            }
        }
        return result;
    }

    public ArrayList<Event> getEventsByPenjual(String penjualId) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getPenjualId().equals(penjualId)) {
                result.add(event);
            }
        }
        return result;
    }

    public ArrayList<Event> searchEvents(String keyword) {
        ArrayList<Event> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Event event : events) {
            if (event.getNamaEvent().toLowerCase().contains(lowerKeyword) ||
                event.getDeskripsi().toLowerCase().contains(lowerKeyword) ||
                event.getKategori().toLowerCase().contains(lowerKeyword)) {
                result.add(event);
            }
        }
        return result;
    }

    public boolean deleteEvent(String eventId) {
        return events.removeIf(event -> event.getEventId().equals(eventId));
    }

    public int getTotalEvents() {
        return events.size();
    }

    // ===== TRANSAKSI MANAGEMENT =====
    public void addTransaksi(Transaksi transaksi) {
        transaksis.add(transaksi);
    }

    public Transaksi getTransaksiById(String transaksiId) {
        for (Transaksi transaksi : transaksis) {
            if (transaksi.getTransaksiId().equals(transaksiId)) {
                return transaksi;
            }
        }
        return null;
    }

    public ArrayList<Transaksi> getAllTransaksis() {
        return new ArrayList<>(transaksis); // Return copy
    }

    public ArrayList<Transaksi> getTransaksisByPembeli(String pembeliId) {
        ArrayList<Transaksi> result = new ArrayList<>();
        for (Transaksi transaksi : transaksis) {
            if (transaksi.getPembeliId().equals(pembeliId)) {
                result.add(transaksi);
            }
        }
        return result;
    }

    public ArrayList<Transaksi> getTransaksisByEvent(String eventId) {
        ArrayList<Transaksi> result = new ArrayList<>();
        for (Transaksi transaksi : transaksis) {
            if (transaksi.getEventId().equals(eventId)) {
                result.add(transaksi);
            }
        }
        return result;
    }

    public int getTotalTransaksis() {
        return transaksis.size();
    }

    // ===== TIKET MANAGEMENT =====
    public void addTiket(Tiket tiket) {
        tikets.add(tiket);
    }

    public Tiket getTiketById(String tiketId) {
        for (Tiket tiket : tikets) {
            if (tiket.getTiketId().equals(tiketId)) {
                return tiket;
            }
        }
        return null;
    }

    public ArrayList<Tiket> getTiketsByPembeli(String pembeliId) {
        ArrayList<Tiket> result = new ArrayList<>();
        for (Tiket tiket : tikets) {
            if (tiket.getPembeliId().equals(pembeliId)) {
                result.add(tiket);
            }
        }
        return result;
    }

    public ArrayList<Tiket> getTiketsByTransaksi(String transaksiId) {
        ArrayList<Tiket> result = new ArrayList<>();
        for (Tiket tiket : tikets) {
            if (tiket.getTransaksiId().equals(transaksiId)) {
                result.add(tiket);
            }
        }
        return result;
    }

    public ArrayList<Tiket> getAllTikets() {
        return new ArrayList<>(tikets); // Return copy
    }

    // ===== UTILITY METHODS =====
    public String generateNewUserId(String role) {
        String prefix = "";
        switch (role) {
            case "Admin": prefix = "ADM"; break;
            case "Penjual": prefix = "PNJ"; break;
            case "Pembeli": prefix = "PMB"; break;
        }
        int count = 1;
        for (User user : users) {
            if (user.getUserId().startsWith(prefix)) {
                count++;
            }
        }
        return prefix + String.format("%03d", count);
    }

    public String generateNewEventId() {
        return "EVT" + String.format("%03d", events.size() + 1);
    }

    public String generateNewTransaksiId() {
        return "TRX" + System.currentTimeMillis();
    }

    public String generateNewTiketId() {
        return "TKT" + System.currentTimeMillis() + String.format("%03d", tikets.size() + 1);
    }
}

