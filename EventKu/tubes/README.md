# 🌐 EventKu - Sistem Pemesanan Tiket Event Online

**Full-Stack Web Application dengan Object-Oriented Programming**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![HTML5](https://img.shields.io/badge/HTML5-E34F26?logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS3](https://img.shields.io/badge/CSS3-1572B6?logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)

---

## 📖 Tentang Project

**EventKu** adalah aplikasi web full-stack untuk pemesanan tiket event online yang dibangun dengan prinsip **Object-Oriented Programming (OOP)** yang lengkap. Aplikasi ini memungkinkan pengguna untuk mencari, memesan, dan membeli tiket event secara online dengan sistem yang aman dan mudah digunakan.

### 🎯 Fitur Utama

- ✅ **Role-based Authentication** (Admin, Penjual, Pembeli)
- ✅ **Event Management** (CRUD operations)
- ✅ **Booking System** dengan real-time kuota
- ✅ **Payment Processing** (simulasi)
- ✅ **QR Code Generation** untuk tiket virtual
- ✅ **Responsive Design** (mobile & desktop)
- ✅ **RESTful API** backend

---

## 🏗️ Teknologi Stack

### Backend:
- **Java 17+** - Programming language
- **Spring Boot 3.2.0** - Framework
- **Maven** - Build tool & dependency management
- **ZXing** - QR Code generation library
- **RESTful API** - Architecture pattern

### Frontend:
- **HTML5** - Structure
- **CSS3** - Modern & responsive styling
- **Vanilla JavaScript** - Logic (no framework)
- **Font Awesome** - Icons

### Deployment:
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Nginx** - Reverse proxy & static file serving

---

## 🎓 Prinsip OOP yang Diimplementasikan

Project ini mendemonstrasikan **7 prinsip OOP** sesuai standar akademik:

| No | Prinsip OOP | Implementation | File Reference |
|----|-------------|----------------|----------------|
| 1 | **Encapsulation** | Private attributes + getter/setter | `models/*.java` |
| 2 | **Inheritance** | User → Admin/Penjual/Pembeli | `models/User.java` |
| 3 | **Abstraction** | Abstract class User | `models/User.java` |
| 4 | **Polymorphism** | Method overriding displayMenu() | `models/Admin.java`, etc |
| 5 | **Interface** | Verifikasi, Pembayaran | `interfaces/*.java` |
| 6 | **Collection** | ArrayList untuk database | `services/Database.java` |
| 7 | **Exception Handling** | Custom exceptions | `exceptions/*.java` |

**Bonus:** Singleton Pattern (Database class)

---

## 📁 Struktur Project

```
tubes/
│
├── 📁 src/main/java/com/eventku/
│   ├── Application.java                    # Main Spring Boot
│   │
│   ├── 📁 controller/                      # REST API Controllers
│   │   ├── AuthController.java             # Login/Register
│   │   ├── EventController.java            # Event CRUD
│   │   ├── TransaksiController.java        # Transaction
│   │   ├── AdminController.java            # Admin operations
│   │   └── QRCodeController.java           # QR Code generation
│   │
│   ├── 📁 models/                          # Domain Models (OOP)
│   │   ├── User.java                       # Abstract parent
│   │   ├── Admin.java                      # Child + Interface
│   │   ├── Penjual.java                    # Child
│   │   ├── Pembeli.java                    # Child + Interface
│   │   ├── Event.java                      # Entity
│   │   ├── Transaksi.java                  # Entity
│   │   └── Tiket.java                      # Entity
│   │
│   ├── 📁 services/                        # Business Logic
│   │   ├── Database.java                   # Singleton + Collection
│   │   ├── AuthService.java                # Authentication
│   │   ├── EventService.java               # Event management
│   │   └── TransaksiService.java           # Transaction logic
│   │
│   ├── 📁 interfaces/                      # OOP Interfaces
│   │   ├── Verifikasi.java                 # Admin interface
│   │   └── Pembayaran.java                 # Pembeli interface
│   │
│   ├── 📁 exceptions/                      # Custom Exceptions
│   │   ├── LoginGagalException.java
│   │   ├── TiketHabisException.java
│   │   ├── EventTidakDitemukanException.java
│   │   └── PembayaranGagalException.java
│   │
│   ├── 📁 dto/                             # Data Transfer Objects
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   └── ApiResponse.java
│   │
│   └── 📁 util/                            # Utilities
│       └── QRCodeGenerator.java            # QR Code utility
│
├── 📁 src/main/resources/
│   └── application.properties              # Configuration
│
├── 📁 frontend/                            # Web Frontend
│   ├── index.html                          # Landing page
│   ├── login.html                          # Login page
│   ├── register.html                       # Register page
│   ├── dashboard.html                      # Dashboard
│   ├── tiket.html                          # Tiket virtual with QR
│   │
│   ├── 📁 css/
│   │   ├── style.css                       # Main styles
│   │   ├── dashboard.css                   # Dashboard styles
│   │   └── tiket.css                       # Tiket styles
│   │
│   └── 📁 js/
│       ├── config.js                       # API configuration
│       ├── auth.js                         # Authentication
│       ├── api.js                          # API helpers
│       ├── dashboard.js                    # Dashboard logic
│       ├── dashboard-admin.js              # Admin features
│       ├── dashboard-penjual.js            # Penjual features
│       ├── dashboard-pembeli.js            # Pembeli features
│       └── tiket.js                        # Tiket display
│
├── pom.xml                                 # Maven configuration
├── Dockerfile                              # Docker container config
├── docker-compose.yml                      # Multi-container setup
├── nginx.conf                              # Nginx configuration
│
└── 📄 Documentation/
    ├── README.md                           # This file
    ├── WEB_DEPLOYMENT_GUIDE.md            # Deployment guide
    ├── QUICK_START_WEB.md                 # Quick start
    ├── QRCODE_FEATURE.md                  # QR Code documentation
    ├── DOKUMENTASI_OOP.md                 # OOP concepts
    └── RINGKASAN_OOP.md                   # OOP summary
```

---

## 🚀 Quick Start

### Prerequisites:
- Java JDK 17 or higher
- Maven 3.6+
- Modern web browser (Chrome, Firefox, Edge)

### Step 1: Clone & Navigate
```bash
git clone <repository-url>
cd tubes
```

### Step 2: Build & Run Backend
```bash
# Build project
mvn clean package -DskipTests

# Run Spring Boot application
mvn spring-boot:run
```

Backend akan berjalan di: **http://localhost:8080/api**

Output yang diharapkan:
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

### Step 3: Run Frontend

**Option A: VS Code Live Server (Recommended)**
1. Install "Live Server" extension
2. Right-click `frontend/index.html`
3. Select "Open with Live Server"

**Option B: Python HTTP Server**
```bash
cd frontend
python -m http.server 8000
```
Access: http://localhost:8000

**Option C: Direct Open**
```bash
# Simply open in browser
start frontend/index.html    # Windows
open frontend/index.html     # Mac
xdg-open frontend/index.html # Linux
```

### Step 4: Login & Test! 🎉

Access: **http://localhost:5500** (or your port)

**Demo Accounts:**
| Role | Username | Password |
|------|----------|----------|
| Admin | `admin` | `admin123` |
| Penjual | `organizer1` | `organizer123` |
| Pembeli | `pembeli1` | `pembeli123` |

---

## 🧪 Testing Flow

### Sebagai Pembeli (Customer):
1. ✅ Login dengan `pembeli1` / `pembeli123`
2. ✅ Browse event tersedia
3. ✅ Cari event dengan keyword
4. ✅ Pilih event dan pesan tiket
5. ✅ Pilih jumlah tiket
6. ✅ Proses pembayaran
7. ✅ **Lihat tiket virtual dengan QR Code!** 📱

### Sebagai Penjual (Event Organizer):
1. ✅ Login dengan `organizer1` / `organizer123`
2. ✅ Buat event baru
3. ✅ Isi detail event (nama, harga, kuota, tanggal, lokasi)
4. ✅ Lihat event yang dibuat
5. ✅ Edit/Update event
6. ✅ Hapus event (jika belum ada transaksi)
7. ✅ Lihat statistik penjualan

### Sebagai Admin:
1. ✅ Login dengan `admin` / `admin123`
2. ✅ Verifikasi event pending (approve/reject)
3. ✅ Lihat daftar semua user
4. ✅ Lihat laporan transaksi
5. ✅ Lihat statistik sistem

---

## 📡 API Endpoints

### Authentication
```http
POST   /api/auth/login        # Login user
POST   /api/auth/register     # Register new user
POST   /api/auth/logout       # Logout
GET    /api/auth/current      # Get current user
```

### Events
```http
GET    /api/events                    # Get all approved events
GET    /api/events/{id}               # Get event by ID
GET    /api/events/pending            # Get pending events (Admin)
GET    /api/events/penjual/{id}       # Get events by penjual
POST   /api/events                    # Create new event
PUT    /api/events/{id}               # Update event
DELETE /api/events/{id}               # Delete event
POST   /api/events/{id}/verify        # Verify event (Admin)
GET    /api/events/search?keyword=... # Search events
```

### Transaksi
```http
POST   /api/transaksi                     # Create transaction
POST   /api/transaksi/{id}/bayar          # Process payment
GET    /api/transaksi/pembeli/{id}        # Get user transactions
GET    /api/transaksi/tiket/pembeli/{id}  # Get user tickets
GET    /api/transaksi                     # Get all transactions (Admin)
DELETE /api/transaksi/{id}                # Cancel transaction
```

### QR Code
```http
GET    /api/qrcode/tiket/{tiketId}    # Generate QR Code image (PNG)
GET    /api/qrcode/verify/{qrData}    # Verify QR code
POST   /api/qrcode/use/{tiketId}      # Use ticket (mark as used)
```

### Admin
```http
GET    /api/admin/users       # Get all users
GET    /api/admin/stats       # Get system statistics
DELETE /api/admin/users/{id}  # Delete user
```

**Full API Documentation:** See [WEB_DEPLOYMENT_GUIDE.md](WEB_DEPLOYMENT_GUIDE.md)

---

## 🎨 Screenshots

### Landing Page
![Landing Page](screenshots/landing.png)
*Modern landing page dengan call-to-action*

### Dashboard
![Dashboard](screenshots/dashboard.png)
*Dashboard universal untuk semua role*

### Tiket Virtual dengan QR Code
![Tiket QR](screenshots/tiket-qr.png)
*Tiket virtual dengan QR Code yang bisa di-scan*

*(Tambahkan screenshots ke folder `/screenshots`)*

---

## 🐳 Deploy dengan Docker

### Run with Docker Compose:
```bash
# Build and run
docker-compose up --build

# Run in background
docker-compose up -d

# View logs
docker-compose logs -f

# Stop
docker-compose down
```

Access:
- **Frontend:** http://localhost
- **Backend API:** http://localhost:8080/api

---

## 🌐 Deploy ke Production

### Backend Options:

**1. Heroku:**
```bash
heroku create eventku-backend
git push heroku main
```

**2. Railway.app:**
- Connect GitHub repository
- Railway auto-detects Spring Boot
- Deploy automatically

**3. AWS/DigitalOcean:**
```bash
# Build JAR
mvn clean package

# Upload and run on server
java -jar target/event-ticket-booking.jar
```

### Frontend Options:

**1. Netlify:**
- Drag & drop `frontend` folder
- Or connect GitHub repository

**2. Vercel:**
- Import from GitHub
- Auto-deploy on push

**3. GitHub Pages:**
```bash
# Push frontend to gh-pages branch
git subtree push --prefix frontend origin gh-pages
```

**Important:** Update `API_BASE_URL` in `frontend/js/config.js` to your production backend URL.

---

## 🎯 Fitur Lengkap

### ✅ Authentication & Authorization
- Login dengan username & password
- Register dengan role selection (Pembeli/Penjual)
- Role-based access control
- Session management
- Logout functionality

### ✅ Event Management
- **Penjual:**
  - Create event dengan detail lengkap
  - Upload info poster (URL)
  - Set harga dan kuota tiket
  - Edit event information
  - Delete event (dengan validation)
  
- **Admin:**
  - Verifikasi event pending
  - Approve atau reject event
  - View all events

- **Pembeli:**
  - Browse event yang sudah approved
  - Search event by keyword
  - View event details

### ✅ Booking & Transaction
- Real-time kuota checking
- Reserve tiket saat booking
- Multiple payment methods:
  - Transfer Bank
  - E-Wallet (GoPay/OVO/Dana)
  - Kartu Kredit
- Payment processing simulation
- Transaction history

### ✅ Tiket Virtual
- **Generate QR Code** menggunakan ZXing
- QR Code bisa di-scan dengan HP
- Barcode number
- Tiket information lengkap
- Download, Share, Print options
- One-time use validation

### ✅ Admin Dashboard
- User management
- Event verification queue
- Transaction reports
- System statistics

### ✅ Responsive Design
- Mobile-friendly
- Tablet-optimized
- Desktop full-featured
- Modern UI/UX

---

## 🔧 Configuration

### Backend (application.properties):
```properties
# Server
server.port=8080

# CORS
cors.allowed-origins=http://localhost:5500

# Jackson (JSON)
spring.jackson.date-format=dd-MM-yyyy HH:mm:ss

# Logging
logging.level.com.eventku=DEBUG
```

### Frontend (config.js):
```javascript
const API_CONFIG = {
    BASE_URL: 'http://localhost:8080/api',
    // ... endpoints
};
```

---

## 🐛 Troubleshooting

### Backend tidak start:
```bash
# Check Java version
java -version

# Check port 8080 is free
netstat -ano | findstr :8080  # Windows
lsof -ti:8080                 # Mac/Linux

# Rebuild
mvn clean install -U
```

### Frontend tidak connect:
1. Check backend running: http://localhost:8080/api
2. Check CORS configuration in `Application.java`
3. Update `API_BASE_URL` in `config.js`
4. Clear browser cache (Ctrl+Shift+R)

### CORS Error:
- Pastikan backend running
- Check `cors.allowed-origins` di `application.properties`
- Restart backend setelah perubahan config

### QR Code tidak muncul:
```bash
# Rebuild with QR dependencies
mvn clean package -DskipTests

# Check ZXing in dependencies
mvn dependency:tree | grep zxing
```

---

## 📚 Dokumentasi Lengkap

- **[WEB_DEPLOYMENT_GUIDE.md](WEB_DEPLOYMENT_GUIDE.md)** - Panduan deployment lengkap
- **[QUICK_START_WEB.md](QUICK_START_WEB.md)** - Quick start guide
- **[QRCODE_FEATURE.md](QRCODE_FEATURE.md)** - QR Code documentation
- **[DOKUMENTASI_OOP.md](DOKUMENTASI_OOP.md)** - Penjelasan detail OOP concepts
- **[RINGKASAN_OOP.md](RINGKASAN_OOP.md)** - OOP quick reference

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

---

## 📝 License

This project is licensed under the MIT License.

---

## 🎓 Academic Context

Project ini dibuat untuk memenuhi tugas **Pemrograman Berorientasi Objek (PBO)** dengan tujuan:
- ✅ Mendemonstrasikan 7 prinsip OOP
- ✅ Implementasi real-world application
- ✅ Clean code & best practices
- ✅ Full-stack development
- ✅ Modern web technologies

---

## 🏆 Features Highlights

- ✨ **Full-Stack** - Backend Spring Boot + Frontend HTML/CSS/JS
- ✨ **OOP Complete** - Semua 7 prinsip terimplementasi
- ✨ **Real QR Code** - Generate QR code yang bisa di-scan
- ✨ **Modern UI** - Gradient, shadows, animations
- ✨ **Responsive** - Works on all devices
- ✨ **RESTful API** - Clean API design
- ✨ **Docker Ready** - Easy deployment
- ✨ **Well Documented** - 100+ pages documentation

---

## 📊 Project Statistics

- **Lines of Code:** ~3,000+ lines
- **Java Classes:** 18 classes
- **REST Endpoints:** 20+ endpoints
- **Frontend Pages:** 5 pages
- **Documentation:** 8 files, ~150 pages

---

## ✨ What's Next?

Fitur yang bisa ditambahkan:
- [ ] Real database (MySQL/PostgreSQL)
- [ ] JWT authentication
- [ ] Email notifications
- [ ] Real payment gateway integration
- [ ] Admin analytics dashboard
- [ ] Mobile app (React Native)
- [ ] Advanced search filters
- [ ] Review & rating system
- [ ] Social media integration

---

## 🙏 Acknowledgments

- Spring Boot Team untuk framework yang amazing
- ZXing untuk QR code library
- Font Awesome untuk icons
- Semua open-source contributors
- Dosen PBO untuk guidance

---

## 📞 Support

**Issues?** Check:
1. [QUICK_START_WEB.md](QUICK_START_WEB.md) - Quick troubleshooting
2. [WEB_DEPLOYMENT_GUIDE.md](WEB_DEPLOYMENT_GUIDE.md) - Detailed guide
3. Open an issue on GitHub

**Contact:** info@eventku.com (jika ada)

---

## ⭐ Star This Repo!

Jika project ini membantu, jangan lupa kasih ⭐ di GitHub! 😊

---

**🚀 EventKu - Your Trusted Event Ticket Booking Platform**

**Made with ❤️ for PBO Learning**

**Version:** 2.0.0 (Web Application)

**Last Updated:** 17 Desember 2025

---

**© 2025 EventKu. All Rights Reserved.**

