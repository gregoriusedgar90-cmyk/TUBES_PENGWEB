package com.eventku.controller;

import com.eventku.dto.*;
import com.eventku.models.*;
import com.eventku.services.AuthService;
import com.eventku.exceptions.LoginGagalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller untuk Authentication
 * Endpoint: /api/auth/*
 * 
 * PRINSIP OOP:
 * - Encapsulation: Logic tersembunyi di service layer
 * - Exception Handling: Try-catch untuk handle errors
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    /**
     * POST /api/auth/login
     * Login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody LoginRequest request) {
        try {
            User user = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(
                ApiResponse.success("Login berhasil", user)
            );
        } catch (LoginGagalException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan server"));
        }
    }

    /**
     * POST /api/auth/register
     * Register endpoint
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {
        try {
            boolean success;
            
            switch (request.getRole()) {
                case "Pembeli":
                    success = authService.register(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        "Pembeli",
                        request.getAlamat(),
                        request.getNomorTelepon()
                    );
                    break;
                    
                case "Penjual":
                    success = authService.register(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        "Penjual",
                        request.getNamaOrganisasi(),
                        request.getNomorTelepon()
                    );
                    break;
                    
                case "Admin":
                    success = authService.register(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        "Admin",
                        request.getAdminLevel()
                    );
                    break;
                    
                default:
                    return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Role tidak valid"));
            }

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Registrasi berhasil", "User created successfully")
                );
            } else {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Registrasi gagal"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Terjadi kesalahan: " + e.getMessage()));
        }
    }

    /**
     * POST /api/auth/logout
     * Logout endpoint
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        authService.logout();
        return ResponseEntity.ok(
            ApiResponse.success("Logout berhasil", null)
        );
    }

    /**
     * GET /api/auth/current
     * Get current logged in user
     */
    @GetMapping("/current")
    public ResponseEntity<ApiResponse<User>> getCurrentUser() {
        User currentUser = authService.getCurrentUser();
        if (currentUser != null) {
            return ResponseEntity.ok(
                ApiResponse.success(currentUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Tidak ada user yang login"));
        }
    }
}

