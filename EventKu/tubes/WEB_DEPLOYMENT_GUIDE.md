# 🚀 Panduan Deployment Website EventKu

## 📋 Overview

Project ini sekarang adalah **Full-Stack Web Application** dengan:
- **Backend**: Spring Boot REST API (Java)
- **Frontend**: HTML/CSS/JavaScript (Vanilla JS)
- **Architecture**: Client-Server dengan RESTful API

---

## 🏗️ Struktur Project Baru

```
tubes/
│
├── 📁 backend/ (Spring Boot)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/eventku/
│       │   ├── Application.java (Main)
│       │   ├── controller/ (REST Controllers)
│       │   ├── models/ (Domain Models - sudah ada)
│       │   ├── services/ (Business Logic - sudah ada)
│       │   ├── exceptions/ (Custom Exceptions - sudah ada)
│       │   ├── interfaces/ (Interfaces - sudah ada)
│       │   └── dto/ (Data Transfer Objects)
│       └── resources/
│           └── application.properties
│
├── 📁 frontend/ (HTML/CSS/JS)
│   ├── index.html (Landing Page)
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── css/
│   │   ├── style.css
│   │   └── dashboard.css
│   └── js/
│       ├── config.js
│       ├── auth.js
│       ├── api.js
│       ├── dashboard.js
│       └── dashboard-*.js
│
└── 📄 Documentation files
```

---

## 🔧 Prerequisites

### Yang Harus Diinstall:

1. **Java JDK 17+**
   ```bash
   java -version  # Check version
   ```
   Download: https://www.oracle.com/java/technologies/downloads/

2. **Maven** (untuk build Spring Boot)
   ```bash
   mvn -version  # Check version
   ```
   Download: https://maven.apache.org/download.cgi

3. **Browser Modern** (Chrome, Firefox, Edge)

---

## 🚀 Cara Menjalankan (Development)

### Step 1: Pindahkan Models ke Package yang Benar

Karena kita sekarang menggunakan Spring Boot, kita perlu memastikan semua class models ada di package `com.eventku.models`:

```bash
# Struktur yang benar:
src/main/java/com/eventku/models/
    ├── User.java
    ├── Admin.java
    ├── Penjual.java
    ├── Pembeli.java
    ├── Event.java
    ├── Transaksi.java
    └── Tiket.java

src/main/java/com/eventku/services/
    ├── Database.java
    ├── AuthService.java
    ├── EventService.java
    └── TransaksiService.java

src/main/java/com/eventku/exceptions/
    ├── LoginGagalException.java
    ├── TiketHabisException.java
    ├── EventTidakDitemukanException.java
    └── PembayaranGagalException.java

src/main/java/com/eventku/interfaces/
    ├── Verifikasi.java
    └── Pembayaran.java
```

### Step 2: Build Backend (Spring Boot)

```bash
# Navigate ke root project
cd tubes/

# Clean dan build dengan Maven
mvn clean package

# Atau jika ada error, skip tests
mvn clean package -DskipTests
```

### Step 3: Run Backend Server

```bash
# Jalankan Spring Boot application
mvn spring-boot:run

# Atau run JAR file
java -jar target/event-ticket-booking.jar
```

Backend akan berjalan di: **http://localhost:8080/api**

Anda akan melihat output:
```
╔════════════════════════════════════════════════════════╗
║                                                        ║
║   🚀 Event Ticket Booking System - RUNNING           ║
║                                                        ║
║   Backend API: http://localhost:8080/api              ║
║   Frontend: Open index.html in browser                ║
║                                                        ║
╚════════════════════════════════════════════════════════╝
```

### Step 4: Run Frontend

Ada beberapa cara:

#### Option 1: Open Langsung di Browser
```bash
# Simply open file
frontend/index.html
```
**Note:** Buka dengan browser modern (Chrome/Firefox/Edge)

#### Option 2: Menggunakan Live Server (Recommended)

Jika menggunakan VS Code:
1. Install extension "Live Server"
2. Right-click pada `frontend/index.html`
3. Pilih "Open with Live Server"

Server akan jalan di: **http://127.0.0.1:5500** atau **http://localhost:5500**

#### Option 3: Python Simple HTTP Server
```bash
cd frontend/
python -m http.server 8000
```
Akses: **http://localhost:8000**

#### Option 4: Node.js http-server
```bash
# Install globally (sekali saja)
npm install -g http-server

# Run
cd frontend/
http-server -p 8000
```

---

## 🧪 Testing Website

### 1. Akses Landing Page
```
http://localhost:5500/index.html
```

### 2. Login dengan Akun Demo:

| Role | Username | Password |
|------|----------|----------|
| **Admin** | admin | admin123 |
| **Penjual** | organizer1 | organizer123 |
| **Pembeli** | pembeli1 | pembeli123 |

### 3. Test Flow Lengkap:

#### Sebagai Admin:
1. Login dengan `admin/admin123`
2. Verifikasi event pending
3. Lihat daftar user
4. Lihat statistik sistem

#### Sebagai Penjual:
1. Login dengan `organizer1/organizer123`
2. Buat event baru
3. Lihat event yang dibuat
4. Edit/Hapus event
5. Lihat statistik penjualan

#### Sebagai Pembeli:
1. Login dengan `pembeli1/pembeli123`
2. Browse event tersedia
3. Cari event
4. Pesan tiket
5. Proses pembayaran
6. Lihat tiket virtual

---

## 🌐 Deployment ke Production

### Option 1: Deploy ke Heroku (Free)

#### Backend (Spring Boot):
```bash
# Install Heroku CLI
# https://devcenter.heroku.com/articles/heroku-cli

# Login
heroku login

# Create app
heroku create eventku-backend

# Push
git push heroku main

# Set environment
heroku config:set SPRING_PROFILES_ACTIVE=production
```

#### Frontend (Netlify):
1. Push frontend folder ke GitHub
2. Connect ke Netlify
3. Deploy
4. Update `API_BASE_URL` di `config.js`

### Option 2: Deploy ke Railway.app

1. Connect GitHub repository
2. Railway auto-detect Spring Boot
3. Deploy otomatis
4. Get public URL

### Option 3: Deploy ke VPS (DigitalOcean/AWS)

#### Backend:
```bash
# Build JAR
mvn clean package

# Upload ke server
scp target/event-ticket-booking.jar user@server:/opt/eventku/

# Run as service
sudo systemctl start eventku
```

#### Frontend:
```bash
# Upload ke Nginx
sudo cp -r frontend/* /var/www/html/eventku/

# Configure Nginx
sudo nano /etc/nginx/sites-available/eventku
```

---

## 🔧 Configuration untuk Production

### 1. Update `application.properties` (Backend):

Create `application-production.properties`:
```properties
# Production settings
server.port=8080
spring.profiles.active=production

# CORS for production
cors.allowed-origins=https://your-frontend-domain.com

# Database (jika pakai real database)
spring.datasource.url=jdbc:mysql://localhost:3306/eventku
spring.datasource.username=root
spring.datasource.password=yourpassword

# Logging
logging.level.root=INFO
logging.file.name=/var/log/eventku/application.log
```

### 2. Update `config.js` (Frontend):

```javascript
const API_CONFIG = {
    BASE_URL: 'https://your-backend-domain.com/api',
    // ... rest of config
};
```

---

## 📊 API Endpoints

### Authentication
```
POST   /api/auth/login       - Login
POST   /api/auth/register    - Register
POST   /api/auth/logout      - Logout
GET    /api/auth/current     - Get current user
```

### Events
```
GET    /api/events           - Get all approved events
GET    /api/events/pending   - Get pending events
GET    /api/events/{id}      - Get event by ID
POST   /api/events           - Create event
PUT    /api/events/{id}      - Update event
DELETE /api/events/{id}      - Delete event
POST   /api/events/{id}/verify - Verify event (Admin)
GET    /api/events/search?keyword=... - Search events
```

### Transaksi
```
POST   /api/transaksi        - Create transaksi
POST   /api/transaksi/{id}/bayar - Process payment
GET    /api/transaksi/pembeli/{id} - Get user transactions
GET    /api/transaksi/tiket/pembeli/{id} - Get user tickets
GET    /api/transaksi        - Get all transactions (Admin)
```

### Admin
```
GET    /api/admin/users      - Get all users
GET    /api/admin/stats      - Get system statistics
DELETE /api/admin/users/{id} - Delete user
```

---

## 🐛 Troubleshooting

### Backend tidak start:
```bash
# Check port 8080 available
netstat -ano | findstr :8080

# Check Java version
java -version

# Rebuild
mvn clean install -U
```

### CORS Error:
- Pastikan `CORS allowed-origins` di `application.properties` sudah benar
- Check `Application.java` CORS configuration

### Frontend tidak connect ke backend:
- Check `API_BASE_URL` di `config.js`
- Check backend running di `http://localhost:8080/api`
- Test API dengan Postman atau curl

### Build Error:
```bash
# Clear Maven cache
mvn dependency:purge-local-repository

# Rebuild
mvn clean install
```

---

## 📚 Resources

- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **REST API Best Practices**: https://restfulapi.net/
- **Deployment Guides**: 
  - Heroku: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
  - Railway: https://railway.app/

---

## ✅ Checklist Deployment

- [ ] Backend compiled successfully
- [ ] Backend running di localhost:8080
- [ ] Frontend bisa diakses
- [ ] Login berhasil dengan akun demo
- [ ] Semua fitur bekerja (browse, create, update, delete)
- [ ] Exception handling bekerja
- [ ] API response correct
- [ ] CORS configured
- [ ] Production config ready
- [ ] Domain & hosting prepared

---

## 🎉 Selesai!

Website EventKu sekarang siap di-deploy sebagai full-stack web application!

**Local URLs:**
- Frontend: http://localhost:5500 atau http://127.0.0.1:5500
- Backend API: http://localhost:8080/api

**Next Steps:**
1. Test semua fitur locally
2. Fix bugs jika ada
3. Deploy ke hosting
4. Update dokumentasi dengan production URLs

---

**© 2025 EventKu - Full Stack Web Application**

