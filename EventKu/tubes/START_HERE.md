# 🚀 START HERE - EventKu Web Application

## Selamat Datang! 👋

Ini adalah project **EventKu** - Sistem Pemesanan Tiket Event Online berbasis web dengan implementasi lengkap **Object-Oriented Programming (OOP)**.

---

## ⚡ Quick Start (5 Menit)

### 1. Install Prerequisites ✅

**Java JDK 17+:**
- Download: https://www.oracle.com/java/technologies/downloads/
- Check: `java -version`

**Maven:**
- Download: https://maven.apache.org/download.cgi
- Check: `mvn -version`

### 2. Run Backend 🔧

```bash
# Navigate ke project folder
cd tubes

# Build (pertama kali)
mvn clean package -DskipTests

# Run Spring Boot
mvn spring-boot:run
```

Tunggu sampai muncul:
```
🚀 Event Ticket Booking System - RUNNING
Backend API: http://localhost:8080/api
```

### 3. Run Frontend 🎨

**Pilih salah satu:**

**A. VS Code Live Server (Recommended):**
1. Install extension "Live Server"
2. Right-click `frontend/index.html`
3. "Open with Live Server"

**B. Python:**
```bash
cd frontend
python -m http.server 8000
```
Access: http://localhost:8000

**C. Langsung buka:**
Double-click `frontend/index.html`

### 4. Login & Test! 🎉

Akses: **http://localhost:5500** (atau port Anda)

**Login dengan demo account:**
```
Admin:    admin / admin123
Penjual:  organizer1 / organizer123
Pembeli:  pembeli1 / pembeli123
```

---

## 🎯 Apa yang Bisa Dilakukan?

### Sebagai Pembeli:
1. ✅ Browse event tersedia
2. ✅ Search event by keyword
3. ✅ Pesan tiket (pilih jumlah)
4. ✅ Proses pembayaran
5. ✅ **Lihat tiket virtual dengan QR Code!** 📱

### Sebagai Penjual (Event Organizer):
1. ✅ Buat event baru
2. ✅ Edit/Update event
3. ✅ Hapus event
4. ✅ Lihat statistik penjualan

### Sebagai Admin:
1. ✅ Verifikasi event pending
2. ✅ Kelola user
3. ✅ Lihat laporan transaksi
4. ✅ Statistik sistem

---

## 📁 Struktur Project

```
tubes/
├── src/main/java/com/eventku/    ← Backend (Spring Boot)
│   ├── Application.java           ← Main class
│   ├── controller/                ← REST API
│   ├── models/                    ← OOP Models
│   ├── services/                  ← Business Logic
│   ├── exceptions/                ← Custom Exceptions
│   └── ...
│
├── frontend/                      ← Frontend (HTML/CSS/JS)
│   ├── index.html                 ← Landing page
│   ├── login.html                 ← Login
│   ├── dashboard.html             ← Dashboard
│   ├── tiket.html                 ← Tiket with QR Code
│   ├── css/                       ← Styles
│   └── js/                        ← JavaScript logic
│
├── pom.xml                        ← Maven config
├── Dockerfile                     ← Docker config
└── docker-compose.yml             ← Multi-container setup
```

---

## 🎓 Prinsip OOP yang Diimplementasikan

✅ **1. Encapsulation** - Private attributes + getter/setter  
✅ **2. Inheritance** - User → Admin/Penjual/Pembeli  
✅ **3. Abstraction** - Abstract class User  
✅ **4. Polymorphism** - Method overriding  
✅ **5. Interface** - Verifikasi, Pembayaran  
✅ **6. Collection** - ArrayList untuk database  
✅ **7. Exception Handling** - Custom exceptions  

**Bonus:** Singleton Pattern (Database class)

---

## 📚 Dokumentasi Lengkap

1. **[README.md](README.md)** ⭐ - Main documentation
2. **[QUICK_START_WEB.md](QUICK_START_WEB.md)** - Quick start guide
3. **[WEB_DEPLOYMENT_GUIDE.md](WEB_DEPLOYMENT_GUIDE.md)** - Deployment guide
4. **[QRCODE_FEATURE.md](QRCODE_FEATURE.md)** - QR Code feature
5. **[DOKUMENTASI_OOP.md](DOKUMENTASI_OOP.md)** - OOP concepts detail
6. **[RINGKASAN_OOP.md](RINGKASAN_OOP.md)** - OOP quick reference

---

## 🐳 Deploy dengan Docker (Easy!)

```bash
# Build and run everything
docker-compose up --build

# Stop
docker-compose down
```

Access:
- Frontend: http://localhost
- Backend: http://localhost:8080/api

---

## ❗ Troubleshooting

### Backend tidak start:
```bash
# Check Java
java -version

# Check port 8080
netstat -ano | findstr :8080  # Windows
lsof -ti:8080                 # Mac/Linux

# Rebuild
mvn clean install
```

### Frontend tidak connect:
1. Check backend running
2. Check `frontend/js/config.js` → BASE_URL
3. Clear browser cache (Ctrl+Shift+R)

### CORS Error:
- Restart backend
- Check `application.properties`

---

## 🎯 Testing Checklist

- [ ] Backend running (localhost:8080)
- [ ] Frontend bisa diakses
- [ ] Login berhasil (3 role)
- [ ] Browse event berhasil
- [ ] Buat event berhasil (penjual)
- [ ] Pesan tiket berhasil (pembeli)
- [ ] **QR Code muncul di tiket** 📱
- [ ] Verifikasi berhasil (admin)

---

## ✨ Fitur Unggulan

🎫 **QR Code Real** - Bisa di-scan dengan HP  
🌐 **Full-Stack** - Backend + Frontend  
📱 **Responsive** - Mobile & Desktop  
🔐 **Secure** - Role-based access  
🎨 **Modern UI** - Gradient & animations  
📊 **Dashboard** - Analytics & reports  

---

## 🎥 Demo Flow

1. **Landing Page** → Click "Login"
2. **Login** → Use demo account
3. **Dashboard** → See role-based menu
4. **Features** → Test sesuai role
5. **Tiket Virtual** → Lihat QR Code!

---

## 🤔 Need Help?

1. Read documentation files
2. Check troubleshooting section
3. Search error di Google
4. Ask dosen/teman

---

## 🎉 Selamat Mencoba!

Project ini **siap pakai** dan **siap di-deploy**!

**Happy Coding! 💻✨**

---

**EventKu - Your Trusted Event Ticket Booking Platform**

© 2025 - Made with ❤️ for PBO Learning

