package com.eventku.controller;

import com.eventku.dto.ApiResponse;
import com.eventku.models.*;
import com.eventku.services.TransaksiService;
import com.eventku.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * REST API Controller untuk Transaksi & Tiket
 * Endpoint: /api/transaksi/*
 */
@RestController
@RequestMapping("/transaksi")
@CrossOrigin(origins = "*")
public class TransaksiController {
    
    private final TransaksiService transaksiService;

    public TransaksiController() {
        this.transaksiService = new TransaksiService();
    }

    /**
     * POST /api/transaksi
     * Create new transaksi
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Transaksi>> createTransaksi(@RequestBody Map<String, Object> request) {
        try {
            String pembeliId = (String) request.get("pembeliId");
            String eventId = (String) request.get("eventId");
            int jumlahTiket = Integer.parseInt(request.get("jumlahTiket").toString());

            Transaksi transaksi = transaksiService.createTransaksi(pembeliId, eventId, jumlahTiket);

            return ResponseEntity.ok(
                ApiResponse.success("Transaksi berhasil dibuat", transaksi)
            );

        } catch (TiketHabisException e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error(e.getMessage()));
        } catch (EventTidakDitemukanException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * POST /api/transaksi/{id}/bayar
     * Process payment
     */
    @PostMapping("/{id}/bayar")
    public ResponseEntity<ApiResponse<String>> prosesPembayaran(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {
        try {
            String metodePembayaran = request.get("metodePembayaran");
            String kodeVerifikasi = request.get("kodeVerifikasi");

            boolean success = transaksiService.prosesPembayaran(id, metodePembayaran, kodeVerifikasi);

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Pembayaran berhasil!", "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Pembayaran gagal"));
            }

        } catch (PembayaranGagalException e) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/transaksi/pembeli/{pembeliId}
     * Get transaction history by pembeli
     */
    @GetMapping("/pembeli/{pembeliId}")
    public ResponseEntity<ApiResponse<ArrayList<Transaksi>>> getRiwayatTransaksi(@PathVariable String pembeliId) {
        try {
            ArrayList<Transaksi> transaksis = transaksiService.getRiwayatTransaksi(pembeliId);
            return ResponseEntity.ok(
                ApiResponse.success(transaksis)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/transaksi/tiket/pembeli/{pembeliId}
     * Get tiket by pembeli
     */
    @GetMapping("/tiket/pembeli/{pembeliId}")
    public ResponseEntity<ApiResponse<ArrayList<Tiket>>> getTiketPembeli(@PathVariable String pembeliId) {
        try {
            ArrayList<Tiket> tikets = transaksiService.getTiketPembeli(pembeliId);
            return ResponseEntity.ok(
                ApiResponse.success(tikets)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/transaksi
     * Get all transaksi (Admin)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ArrayList<Transaksi>>> getAllTransaksi() {
        try {
            ArrayList<Transaksi> transaksis = transaksiService.getAllTransaksi();
            return ResponseEntity.ok(
                ApiResponse.success(transaksis)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * DELETE /api/transaksi/{id}
     * Cancel transaksi
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> batalkanTransaksi(
            @PathVariable String id,
            @RequestParam String userId) {
        try {
            boolean success = transaksiService.batalkanTransaksi(id, userId);

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Transaksi berhasil dibatalkan", "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal membatalkan transaksi"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }
}

