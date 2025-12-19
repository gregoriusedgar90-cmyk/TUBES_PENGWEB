<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\ReportController;
use App\Http\Controllers\LocationController;
use App\Http\Controllers\SocialController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| 1. Open your Laravel project.
| 2. Go to 'routes/api.php'.
| 3. Replace the entire file content with the code below.
|
*/

// --- Public Routes (No Login Required) ---

// Authentication
Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);

// Location Data (For Dropdowns)
Route::get('/provinces', [LocationController::class, 'getProvinces']);
Route::get('/regencies', [LocationController::class, 'getRegencies']);

// Public Report Viewing
Route::get('/reports', [ReportController::class, 'index']); // Feed
Route::get('/reports/{id}', [ReportController::class, 'show']); // Details
Route::get('/leaderboard', [SocialController::class, 'leaderboard']); // Rankings


// --- Protected Routes (Login Required) ---
Route::middleware('auth:sanctum')->group(function () {
    
    // User Profile
    Route::get('/user', function (Request $request) {
        return $request->user();
    });
    Route::get('/profile', [AuthController::class, 'profile']);
    Route::put('/profile', [AuthController::class, 'updateProfile']);

    // Submitting Reports
    Route::post('/reports', [ReportController::class, 'store']); 
    
    // Updating Report Progress (Admin/Owner)
    Route::post('/reports/{id}/progress', [ReportController::class, 'addProgress']);

    // Social Interactions
    Route::post('/reports/{id}/like', [SocialController::class, 'toggleLike']);
    Route::post('/reports/{id}/comment', [SocialController::class, 'addComment']);

    // Logout
    Route::post('/logout', [AuthController::class, 'logout']);
});