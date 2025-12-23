# 🚀 Quick Start - Web Version

## Cara Tercepat Menjalankan Website EventKu

### ⚡ Super Quick (5 Menit)

#### 1. Install Prerequisites
- **Java JDK 17+**: https://www.oracle.com/java/technologies/downloads/
- **Maven**: https://maven.apache.org/download.cgi

Cek instalasi:
```bash
java -version
mvn -version
```

#### 2. Pindahkan Files ke Struktur Spring Boot

Buat struktur folder:
```
src/main/java/com/eventku/
    ├── models/       (copy dari src/models/*.java)
    ├── services/     (copy dari src/services/*.java)
    ├── exceptions/   (copy dari src/exceptions/*.java)
    └── interfaces/   (copy dari src/interfaces/*.java)
```

Tambahkan `package com.eventku.models;` (dan sesuaikan) di setiap file.

#### 3. Run Backend

```bash
# Build (pertama kali saja)
mvn clean package -DskipTests

# Run
mvn spring-boot:run
```

Tunggu sampai muncul:
```
🚀 Event Ticket Booking System - RUNNING
Backend API: http://localhost:8080/api
```

#### 4. Run Frontend

**Option A: Live Server (VS Code)**
- Install extension "Live Server"
- Right-click `frontend/index.html`
- "Open with Live Server"

**Option B: Python**
```bash
cd frontend
python -m http.server 8000
```
Akses: http://localhost:8000

**Option C: Langsung Buka**
- Double-click `frontend/index.html`

#### 5. Login & Test

Buka browser: http://localhost:5500 (atau port Anda)

**Login dengan akun demo:**
- Admin: `admin` / `admin123`
- Penjual: `organizer1` / `organizer123`
- Pembeli: `pembeli1` / `pembeli123`

---

## 🐳 Menggunakan Docker (Lebih Mudah!)

Jika sudah install Docker:

```bash
# Build dan run semuanya
docker-compose up --build

# Atau run di background
docker-compose up -d
```

Akses:
- Frontend: http://localhost
- Backend: http://localhost:8080/api

Stop:
```bash
docker-compose down
```

---

## 📱 Test Fitur

### Sebagai Pembeli:
1. Login dengan `pembeli1` / `pembeli123`
2. Browse event
3. Klik event → Pesan Tiket
4. Pilih jumlah tiket
5. Pilih metode pembayaran
6. Lihat tiket virtual

### Sebagai Penjual:
1. Login dengan `organizer1` / `organizer123`
2. Buat Event Baru
3. Isi form (nama, harga, kuota, tanggal, dll)
4. Submit
5. Lihat di "Event Saya"

### Sebagai Admin:
1. Login dengan `admin` / `admin123`
2. Verifikasi Event Pending
3. Approve event
4. Lihat statistik sistem

---

## ❗ Troubleshooting

### Backend Error "Port 8080 already in use"
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

### Frontend tidak connect ke backend
1. Check backend running: http://localhost:8080/api
2. Check `frontend/js/config.js` → BASE_URL harus `http://localhost:8080/api`
3. Clear browser cache (Ctrl+Shift+R)

### CORS Error
- Sudah di-handle di `Application.java`
- Jika masih error, restart backend

### Maven Build Error
```bash
mvn clean install -U
mvn dependency:purge-local-repository
```

---

## 🎯 File Penting

```
pom.xml                          → Maven config
src/main/java/com/eventku/
    Application.java             → Main Spring Boot
    controller/                  → REST API endpoints
    
src/main/resources/
    application.properties       → Config

frontend/
    index.html                   → Landing page
    login.html                   → Login page
    dashboard.html               → Dashboard
    js/config.js                 → API config
```

---

## 🌐 Deploy ke Internet

### Heroku (Free):
```bash
heroku login
heroku create eventku-app
git push heroku main
```

### Railway.app (Easy):
1. Push ke GitHub
2. Connect ke Railway
3. Deploy otomatis

### Vercel (Frontend) + Railway (Backend):
- Frontend → Vercel
- Backend → Railway
- Update `API_BASE_URL` di config.js

---

## 📚 Dokumentasi Lengkap

- **WEB_DEPLOYMENT_GUIDE.md** - Full deployment guide
- **README.md** - Original console app guide
- **API Endpoints** - Lihat di WEB_DEPLOYMENT_GUIDE.md

---

## ✅ Checklist

- [ ] Java & Maven installed
- [ ] Backend running (localhost:8080)
- [ ] Frontend running (localhost:5500 or similar)
- [ ] Login berhasil
- [ ] Browse event berhasil
- [ ] Buat event berhasil (penjual)
- [ ] Pesan tiket berhasil (pembeli)
- [ ] Verifikasi berhasil (admin)

---

**Selamat! Website EventKu sudah berjalan! 🎉**

**Support:** Baca WEB_DEPLOYMENT_GUIDE.md untuk detail lebih lanjut.

