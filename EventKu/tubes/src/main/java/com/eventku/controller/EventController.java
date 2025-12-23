package com.eventku.controller;

import com.eventku.dto.ApiResponse;
import com.eventku.models.Event;
import com.eventku.services.EventService;
import com.eventku.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * REST API Controller untuk Event Management
 * Endpoint: /api/events/*
 * 
 * PRINSIP OOP:
 * - Separation of Concerns: Controller hanya handle HTTP
 * - Service layer handle business logic
 */
@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventController {
    
    private final EventService eventService;

    public EventController() {
        this.eventService = new EventService();
    }

    /**
     * GET /api/events
     * Get all approved events (untuk browse)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ArrayList<Event>>> getAllEvents() {
        try {
            ArrayList<Event> events = eventService.browseEvents();
            return ResponseEntity.ok(
                ApiResponse.success("Success", events)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/events/pending
     * Get pending events (untuk Admin)
     */
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<ArrayList<Event>>> getPendingEvents() {
        try {
            ArrayList<Event> events = eventService.getPendingEvents();
            return ResponseEntity.ok(
                ApiResponse.success(events)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/events/{id}
     * Get event by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable String id) {
        try {
            Event event = com.eventku.services.Database.getInstance().getEventById(id);
            if (event != null) {
                return ResponseEntity.ok(
                    ApiResponse.success(event)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Event tidak ditemukan"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/events/penjual/{penjualId}
     * Get events by penjual ID
     */
    @GetMapping("/penjual/{penjualId}")
    public ResponseEntity<ApiResponse<ArrayList<Event>>> getEventsByPenjual(@PathVariable String penjualId) {
        try {
            ArrayList<Event> events = eventService.getEventsByPenjual(penjualId);
            return ResponseEntity.ok(
                ApiResponse.success(events)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * POST /api/events
     * Create new event
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody Map<String, Object> request) {
        try {
            String penjualId = (String) request.get("penjualId");
            String namaEvent = (String) request.get("namaEvent");
            String deskripsi = (String) request.get("deskripsi");
            double hargaTiket = Double.parseDouble(request.get("hargaTiket").toString());
            int kuotaTotal = Integer.parseInt(request.get("kuotaTotal").toString());
            String lokasi = (String) request.get("lokasi");
            String tanggalEventStr = (String) request.get("tanggalEvent");
            String kategori = (String) request.get("kategori");
            String posterUrl = (String) request.get("posterUrl");

            // Parse tanggal
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime tanggalEvent = LocalDateTime.parse(tanggalEventStr, formatter);

            Event event = eventService.createEvent(
                penjualId, namaEvent, deskripsi, hargaTiket,
                kuotaTotal, lokasi, tanggalEvent, kategori, posterUrl
            );

            if (event != null) {
                return ResponseEntity.ok(
                    ApiResponse.success("Event berhasil dibuat", event)
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal membuat event"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * PUT /api/events/{id}
     * Update event
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateEvent(
            @PathVariable String id,
            @RequestBody Map<String, Object> request) {
        try {
            String penjualId = (String) request.get("penjualId");
            String namaEvent = (String) request.get("namaEvent");
            String deskripsi = (String) request.get("deskripsi");
            double hargaTiket = Double.parseDouble(request.get("hargaTiket").toString());
            String lokasi = (String) request.get("lokasi");

            boolean success = eventService.updateEvent(
                id, penjualId, namaEvent, deskripsi, hargaTiket, lokasi
            );

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Event berhasil diupdate", "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal mengupdate event"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * DELETE /api/events/{id}
     * Delete event
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEvent(
            @PathVariable String id,
            @RequestParam String penjualId) {
        try {
            boolean success = eventService.deleteEvent(id, penjualId);

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Event berhasil dihapus", "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal menghapus event"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * POST /api/events/{id}/verify
     * Verify event (Admin only)
     */
    @PostMapping("/{id}/verify")
    public ResponseEntity<ApiResponse<String>> verifyEvent(
            @PathVariable String id,
            @RequestParam boolean approve) {
        try {
            boolean success = eventService.verifyEvent(id, approve);

            if (success) {
                String message = approve ? "Event disetujui" : "Event ditolak";
                return ResponseEntity.ok(
                    ApiResponse.success(message, "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal memverifikasi event"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/events/search
     * Search events by keyword
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<ArrayList<Event>>> searchEvents(@RequestParam String keyword) {
        try {
            ArrayList<Event> events = eventService.searchEvents(keyword);
            return ResponseEntity.ok(
                ApiResponse.success(events)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }
}

