package com.eventku.controller;

import com.eventku.dto.ApiResponse;
import com.eventku.models.User;
import com.eventku.services.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API Controller untuk Admin Operations
 * Endpoint: /api/admin/*
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    private final Database database;

    public AdminController() {
        this.database = Database.getInstance();
    }

    /**
     * GET /api/admin/users
     * Get all users
     */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<ArrayList<User>>> getAllUsers() {
        try {
            ArrayList<User> users = database.getAllUsers();
            return ResponseEntity.ok(
                ApiResponse.success(users)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * GET /api/admin/stats
     * Get system statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> getStatistics() {
        try {
            Map<String, Integer> stats = new HashMap<>();
            stats.put("totalUsers", database.getTotalUsers());
            stats.put("totalEvents", database.getTotalEvents());
            stats.put("totalTransaksis", database.getTotalTransaksis());

            return ResponseEntity.ok(
                ApiResponse.success(stats)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * DELETE /api/admin/users/{userId}
     * Delete user
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String userId) {
        try {
            boolean success = database.deleteUser(userId);

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("User berhasil dihapus", "Success")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Gagal menghapus user"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }
}

