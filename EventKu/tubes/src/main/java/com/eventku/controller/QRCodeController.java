package com.eventku.controller;

import com.eventku.models.Tiket;
import com.eventku.services.Database;
import com.eventku.util.QRCodeGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller untuk QR Code Generation
 * Endpoint: /api/qrcode/*
 */
@RestController
@RequestMapping("/qrcode")
@CrossOrigin(origins = "*")
public class QRCodeController {
    
    private final Database database;

    public QRCodeController() {
        this.database = Database.getInstance();
    }

    /**
     * GET /api/qrcode/tiket/{tiketId}
     * Generate dan return QR code image untuk tiket
     */
    @GetMapping("/tiket/{tiketId}")
    public ResponseEntity<byte[]> generateTiketQRCode(@PathVariable String tiketId) {
        try {
            // Get tiket from database
            Tiket tiket = database.getTiketById(tiketId);
            
            if (tiket == null) {
                return ResponseEntity.notFound().build();
            }

            // Generate QR data
            String qrData = QRCodeGenerator.generateTiketQRData(
                tiket.getTiketId(),
                tiket.getEventId(),
                tiket.getPembeliId()
            );

            // Generate QR code image
            byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrData, 400, 400);

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(qrCodeImage.length);
            headers.set("Cache-Control", "max-age=3600"); // Cache for 1 hour

            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET /api/qrcode/verify/{qrData}
     * Verify QR code data
     */
    @GetMapping("/verify/{qrData}")
    public ResponseEntity<String> verifyQRCode(@PathVariable String qrData) {
        try {
            boolean isValid = QRCodeGenerator.validateQRData(qrData);
            
            if (isValid) {
                // Extract tiket ID from QR data
                String tiketId = qrData.split("\\|")[0].replace("TIKET-", "");
                Tiket tiket = database.getTiketById(tiketId);
                
                if (tiket != null && !tiket.isUsed()) {
                    return ResponseEntity.ok("QR Code Valid - Tiket untuk: " + tiket.getNamaEvent());
                } else if (tiket != null && tiket.isUsed()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("QR Code sudah digunakan!");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Tiket tidak ditemukan!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("QR Code tidak valid!");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * POST /api/qrcode/use/{tiketId}
     * Mark tiket as used (scan at entrance)
     */
    @PostMapping("/use/{tiketId}")
    public ResponseEntity<String> useTiket(@PathVariable String tiketId) {
        try {
            Tiket tiket = database.getTiketById(tiketId);
            
            if (tiket == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tiket tidak ditemukan!");
            }

            if (tiket.isUsed()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Tiket sudah digunakan sebelumnya!");
            }

            // Mark as used
            tiket.gunakan();

            return ResponseEntity.ok("Tiket berhasil digunakan! Selamat menikmati: " + tiket.getNamaEvent());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

