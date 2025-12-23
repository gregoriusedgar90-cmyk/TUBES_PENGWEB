package services;

import models.*;
import exceptions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * SERVICE LAYER:
 * EventService menangani semua operasi terkait Event
 * Menerapkan business logic dan validation
 */
public class EventService {
    private Database database;

    public EventService() {
        this.database = Database.getInstance();
    }

    /**
     * Method untuk membuat event baru (oleh Penjual)
     */
    public Event createEvent(String penjualId, String namaEvent, String deskripsi,
                            double hargaTiket, int kuotaTotal, String lokasi,
                            LocalDateTime tanggalEvent, String kategori, String posterUrl) {
        try {
            // Validasi input
            if (namaEvent == null || namaEvent.isEmpty()) {
                System.out.println("✗ Nama event tidak boleh kosong!");
                return null;
            }
            if (hargaTiket < 0) {
                System.out.println("✗ Harga tiket tidak valid!");
                return null;
            }
            if (kuotaTotal <= 0) {
                System.out.println("✗ Kuota tiket harus lebih dari 0!");
                return null;
            }
            if (tanggalEvent.isBefore(LocalDateTime.now())) {
                System.out.println("✗ Tanggal event tidak boleh di masa lalu!");
                return null;
            }

            // Generate event ID
            String eventId = database.generateNewEventId();

            // Buat event baru
            Event newEvent = new Event(eventId, namaEvent, deskripsi, hargaTiket,
                                      kuotaTotal, lokasi, tanggalEvent, kategori,
                                      posterUrl, penjualId);

            // Simpan ke database
            database.addEvent(newEvent);

            // Update penjual
            User user = database.getUserById(penjualId);
            if (user instanceof Penjual) {
                ((Penjual) user).tambahEventId(eventId);
            }

            System.out.println("\n✓ Event berhasil dibuat!");
            System.out.println("Event ID: " + eventId);
            System.out.println("Status: Pending (Menunggu verifikasi admin)");
            
            return newEvent;

        } catch (Exception e) {
            System.out.println("✗ Gagal membuat event: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method untuk update event
     */
    public boolean updateEvent(String eventId, String penjualId, String namaEvent,
                              String deskripsi, double hargaTiket, String lokasi) {
        try {
            Event event = database.getEventById(eventId);
            
            if (event == null) {
                throw new EventTidakDitemukanException("Event tidak ditemukan!");
            }

            // Validasi ownership
            if (!event.getPenjualId().equals(penjualId)) {
                System.out.println("✗ Anda tidak memiliki akses untuk mengedit event ini!");
                return false;
            }

            // Update data
            if (namaEvent != null && !namaEvent.isEmpty()) {
                event.setNamaEvent(namaEvent);
            }
            if (deskripsi != null) {
                event.setDeskripsi(deskripsi);
            }
            if (hargaTiket >= 0) {
                event.setHargaTiket(hargaTiket);
            }
            if (lokasi != null) {
                event.setLokasi(lokasi);
            }

            System.out.println("✓ Event berhasil diupdate!");
            return true;

        } catch (EventTidakDitemukanException e) {
            System.out.println("✗ " + e.getMessage());
            return false;
        }
    }

    /**
     * Method untuk delete event
     */
    public boolean deleteEvent(String eventId, String penjualId) {
        try {
            Event event = database.getEventById(eventId);
            
            if (event == null) {
                throw new EventTidakDitemukanException("Event tidak ditemukan!");
            }

            // Validasi ownership
            if (!event.getPenjualId().equals(penjualId)) {
                System.out.println("✗ Anda tidak memiliki akses untuk menghapus event ini!");
                return false;
            }

            // Cek apakah ada transaksi terkait
            ArrayList<Transaksi> transaksis = database.getTransaksisByEvent(eventId);
            if (!transaksis.isEmpty()) {
                System.out.println("✗ Tidak dapat menghapus event yang sudah memiliki transaksi!");
                return false;
            }

            // Hapus dari database
            database.deleteEvent(eventId);

            // Update penjual
            User user = database.getUserById(penjualId);
            if (user instanceof Penjual) {
                ((Penjual) user).hapusEventId(eventId);
            }

            System.out.println("✓ Event berhasil dihapus!");
            return true;

        } catch (EventTidakDitemukanException e) {
            System.out.println("✗ " + e.getMessage());
            return false;
        }
    }

    /**
     * Method untuk browse event (yang sudah approved)
     */
    public ArrayList<Event> browseEvents() {
        return database.getEventsByStatus("Approved");
    }

    /**
     * Method untuk search event
     */
    public ArrayList<Event> searchEvents(String keyword) {
        ArrayList<Event> allEvents = database.searchEvents(keyword);
        // Filter hanya yang approved
        ArrayList<Event> approvedEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (event.getStatusVerifikasi().equals("Approved")) {
                approvedEvents.add(event);
            }
        }
        return approvedEvents;
    }

    /**
     * Method untuk verifikasi event (oleh Admin)
     */
    public boolean verifyEvent(String eventId, boolean approve) {
        try {
            Event event = database.getEventById(eventId);
            
            if (event == null) {
                throw new EventTidakDitemukanException("Event tidak ditemukan!");
            }

            if (approve) {
                event.setStatusVerifikasi("Approved");
                System.out.println("✓ Event telah disetujui!");
            } else {
                event.setStatusVerifikasi("Rejected");
                System.out.println("✓ Event ditolak!");
            }

            return true;

        } catch (EventTidakDitemukanException e) {
            System.out.println("✗ " + e.getMessage());
            return false;
        }
    }

    /**
     * Method untuk mendapatkan event berdasarkan penjual
     */
    public ArrayList<Event> getEventsByPenjual(String penjualId) {
        return database.getEventsByPenjual(penjualId);
    }

    /**
     * Method untuk mendapatkan pending events (untuk Admin)
     */
    public ArrayList<Event> getPendingEvents() {
        return database.getEventsByStatus("Pending");
    }
}

