# 📚 INDEX - Sistem Pemesanan Tiket Event Online

## Panduan Navigasi Dokumentasi

Selamat datang! Project ini berisi implementasi lengkap **Object-Oriented Programming (OOP)** dalam Java untuk sistem pemesanan tiket event online. Berikut panduan untuk menavigasi dokumentasi:

---

## 🚀 Mulai dari Mana?

### Untuk Pemula / First Time User:
1. **Baca ini dulu:** [`README.md`](README.md)
   - Overview project
   - Penjelasan fitur
   - Prinsip OOP yang digunakan

2. **Cara menjalankan:** [`CARA_COMPILE_DAN_RUN.md`](CARA_COMPILE_DAN_RUN.md)
   - Step-by-step compile
   - Cara run program
   - Troubleshooting umum

3. **Testing:** [`QUICK_START.md`](QUICK_START.md)
   - Skenario testing lengkap
   - Data dummy untuk login
   - Tips testing

---

### Untuk Memahami OOP:
1. **Ringkasan:** [`RINGKASAN_OOP.md`](RINGKASAN_OOP.md)
   - Quick reference semua prinsip OOP
   - Contoh kode singkat
   - Line number reference
   - ⭐ **TERBAIK untuk presentasi!**

2. **Detail lengkap:** [`DOKUMENTASI_OOP.md`](DOKUMENTASI_OOP.md)
   - Penjelasan mendalam setiap prinsip
   - Diagram dan flow
   - Multiple examples
   - Best practices

3. **Struktur project:** [`STRUKTUR_PROJECT.txt`](STRUKTUR_PROJECT.txt)
   - Tree structure lengkap
   - Penjelasan setiap file
   - Alur kerja aplikasi
   - Data dummy

---

## 📂 Struktur Dokumentasi

```
📁 Documentation Files:
│
├── 📄 INDEX.md (File ini)
│   └─ Navigasi ke semua dokumentasi
│
├── 📄 README.md ⭐ START HERE
│   ├─ Overview project
│   ├─ Fitur berdasarkan role
│   ├─ Cara menjalankan
│   └─ Penjelasan konsep OOP
│
├── 📄 CARA_COMPILE_DAN_RUN.md
│   ├─ Cara compile (Windows/Linux)
│   ├─ Cara run program
│   ├─ Troubleshooting
│   └─ Tips demo/presentasi
│
├── 📄 QUICK_START.md
│   ├─ Testing scenario
│   ├─ Data dummy accounts
│   ├─ Step-by-step testing
│   └─ Exception testing
│
├── 📄 RINGKASAN_OOP.md ⭐ BEST FOR PRESENTATION
│   ├─ Quick reference OOP
│   ├─ Code examples dengan line numbers
│   ├─ Checklist presentasi
│   └─ Tips Q&A
│
├── 📄 DOKUMENTASI_OOP.md
│   ├─ Detailed explanation
│   ├─ Diagrams & flows
│   ├─ Multiple examples
│   └─ Best practices
│
└── 📄 STRUKTUR_PROJECT.txt
    ├─ Complete file tree
    ├─ File descriptions
    ├─ Application flow
    └─ Dummy data info
```

---

## 🎯 Dokumentasi Berdasarkan Kebutuhan

### 1. "Saya ingin menjalankan program"
→ Buka: [`CARA_COMPILE_DAN_RUN.md`](CARA_COMPILE_DAN_RUN.md)

### 2. "Saya ingin tahu fitur apa saja"
→ Buka: [`README.md`](README.md) bagian "Fitur Berdasarkan Role"

### 3. "Saya ingin test semua fitur"
→ Buka: [`QUICK_START.md`](QUICK_START.md)

### 4. "Saya perlu explain OOP untuk presentasi"
→ Buka: [`RINGKASAN_OOP.md`](RINGKASAN_OOP.md) ⭐

### 5. "Saya ingin memahami OOP secara mendalam"
→ Buka: [`DOKUMENTASI_OOP.md`](DOKUMENTASI_OOP.md)

### 6. "Saya ingin tahu struktur project"
→ Buka: [`STRUKTUR_PROJECT.txt`](STRUKTUR_PROJECT.txt)

### 7. "Program saya error"
→ Buka: [`CARA_COMPILE_DAN_RUN.md`](CARA_COMPILE_DAN_RUN.md) bagian "Troubleshooting"

---

## 🗂️ Struktur Source Code

```
📁 src/
│
├── 📄 Main.java
│   └─ Entry point, menu handler, demonstrasi polymorphism
│
├── 📁 models/ (Domain Models)
│   ├── 📄 User.java          [Abstract Parent]
│   ├── 📄 Admin.java         [Child + Interface Verifikasi]
│   ├── 📄 Penjual.java       [Child]
│   ├── 📄 Pembeli.java       [Child + Interface Pembayaran]
│   ├── 📄 Event.java         [Entity]
│   ├── 📄 Transaksi.java     [Entity]
│   └── 📄 Tiket.java         [Entity]
│
├── 📁 interfaces/
│   ├── 📄 Verifikasi.java    [Interface for Admin]
│   └── 📄 Pembayaran.java    [Interface for Pembeli]
│
├── 📁 services/ (Business Logic)
│   ├── 📄 Database.java      [Singleton + ArrayList]
│   ├── 📄 AuthService.java   [Login/Register]
│   ├── 📄 EventService.java  [Event CRUD]
│   └── 📄 TransaksiService.java [Transaction & Ticket]
│
└── 📁 exceptions/ (Custom Exceptions)
    ├── 📄 LoginGagalException.java
    ├── 📄 TiketHabisException.java
    ├── 📄 EventTidakDitemukanException.java
    └── 📄 PembayaranGagalException.java
```

---

## 🎓 Prinsip OOP yang Diimplementasikan

| No | Prinsip | File Utama | Baca Detail |
|----|---------|-----------|-------------|
| 1 | **Encapsulation** | models/*.java | [RINGKASAN_OOP.md#1](RINGKASAN_OOP.md#1️⃣-encapsulation-enkapsulasi) |
| 2 | **Inheritance** | User → Admin/Penjual/Pembeli | [RINGKASAN_OOP.md#2](RINGKASAN_OOP.md#2️⃣-inheritance-pewarisan) |
| 3 | **Abstraction** | User.java (abstract) | [RINGKASAN_OOP.md#3](RINGKASAN_OOP.md#3️⃣-abstraction-abstraksi) |
| 4 | **Polymorphism** | Main.java displayMenu() | [RINGKASAN_OOP.md#4](RINGKASAN_OOP.md#4️⃣-polymorphism-polimorfisme) |
| 5 | **Interface** | Verifikasi, Pembayaran | [RINGKASAN_OOP.md#5](RINGKASAN_OOP.md#5️⃣-interface) |
| 6 | **Collection** | Database.java (ArrayList) | [RINGKASAN_OOP.md#6](RINGKASAN_OOP.md#6️⃣-collection-arraylist) |
| 7 | **Exception** | exceptions/*.java | [RINGKASAN_OOP.md#7](RINGKASAN_OOP.md#7️⃣-exception-handling) |
| 8 | **Singleton** | Database.java | [RINGKASAN_OOP.md#bonus](RINGKASAN_OOP.md#-bonus-design-pattern) |

---

## ⚡ Quick Actions

### Compile Program:
```bash
# Windows
.\compile.bat

# Linux/Mac
./compile.sh
```

### Run Program:
```bash
# Windows
.\run.bat

# Linux/Mac
./run.sh
```

### Login Test Accounts:
```
Admin:    username: admin        password: admin123
Penjual:  username: organizer1   password: organizer123
Pembeli:  username: pembeli1     password: pembeli123
```

---

## 📊 Statistik Project

- **Total Files:** 26 files
  - Java Source: 18 files
  - Documentation: 6 files
  - Scripts: 4 files

- **Lines of Code:** ~2,500+ lines

- **Classes:** 18 classes
  - Models: 7
  - Services: 4
  - Interfaces: 2
  - Exceptions: 4
  - Main: 1

- **OOP Principles:** 7 + 1 design pattern

---

## 🎯 Roadmap Membaca Dokumentasi

### Path 1: Quick Start (15 menit)
```
README.md → CARA_COMPILE_DAN_RUN.md → Run Program → Test Login
```

### Path 2: Understanding OOP (30 menit)
```
RINGKASAN_OOP.md → Baca kode di src/ → Test fitur
```

### Path 3: Deep Dive (60+ menit)
```
README.md → DOKUMENTASI_OOP.md → STRUKTUR_PROJECT.txt → 
Baca semua source code → Test semua skenario
```

### Path 4: Preparation for Presentation (45 menit)
```
RINGKASAN_OOP.md → Siapkan demo → Checklist presentasi →
Practice Q&A
```

---

## 💡 Tips Menggunakan Dokumentasi

1. **Bookmark file** yang sering digunakan
2. **Gunakan Ctrl+F** untuk search keyword
3. **Ikuti line number reference** untuk cek kode langsung
4. **Test sambil baca** dokumentasi untuk pemahaman lebih baik
5. **Baca comments di kode** untuk context tambahan

---

## 🔗 Link ke File Penting

### Documentation:
- [README.md](README.md) - Main documentation
- [RINGKASAN_OOP.md](RINGKASAN_OOP.md) - OOP quick reference ⭐
- [DOKUMENTASI_OOP.md](DOKUMENTASI_OOP.md) - OOP detailed explanation
- [QUICK_START.md](QUICK_START.md) - Testing guide
- [CARA_COMPILE_DAN_RUN.md](CARA_COMPILE_DAN_RUN.md) - Compile & run guide
- [STRUKTUR_PROJECT.txt](STRUKTUR_PROJECT.txt) - Project structure

### Source Code:
- [src/Main.java](src/Main.java) - Entry point
- [src/models/User.java](src/models/User.java) - Abstract parent
- [src/services/Database.java](src/services/Database.java) - Singleton database

### Scripts:
- [compile.bat](compile.bat) - Windows compile
- [run.bat](run.bat) - Windows run
- [compile.sh](compile.sh) - Linux/Mac compile
- [run.sh](run.sh) - Linux/Mac run

---

## 📞 Support & Help

### Jika menemui masalah:

1. **Error saat compile:**
   → Buka [CARA_COMPILE_DAN_RUN.md](CARA_COMPILE_DAN_RUN.md) bagian "Troubleshooting"

2. **Tidak tahu cara test:**
   → Buka [QUICK_START.md](QUICK_START.md) untuk skenario lengkap

3. **Tidak paham konsep OOP:**
   → Buka [DOKUMENTASI_OOP.md](DOKUMENTASI_OOP.md) untuk penjelasan detail

4. **Persiapan presentasi:**
   → Buka [RINGKASAN_OOP.md](RINGKASAN_OOP.md) bagian "Checklist untuk Presentasi"

---

## ✅ Checklist Pertama Kali

- [ ] Baca README.md untuk overview
- [ ] Install Java JDK (jika belum)
- [ ] Run compile.bat / compile.sh
- [ ] Run run.bat / run.sh
- [ ] Login dengan akun test (admin/admin123)
- [ ] Test satu fitur (browse event)
- [ ] Baca RINGKASAN_OOP.md
- [ ] Test exception handling (login password salah)
- [ ] Explore semua role (Admin, Penjual, Pembeli)
- [ ] Siap untuk presentasi! 🎉

---

## 🏆 Best Practices

Untuk mendapat pemahaman maksimal:

1. ✅ **Baca dokumentasi sambil buka kode**
2. ✅ **Run program sambil baca**
3. ✅ **Test setiap fitur yang dijelaskan**
4. ✅ **Coba trigger exception untuk lihat handling**
5. ✅ **Trace code flow dari Main → Service → Database**
6. ✅ **Perhatikan comment di kode (menjelaskan prinsip OOP)**

---

## 🎉 Selamat Belajar!

Project ini dirancang untuk mendemonstrasikan implementasi lengkap **7 prinsip OOP** dalam satu aplikasi yang kohesif. Setiap prinsip diimplementasikan dengan benar sesuai standar akademik dan best practices.

**Semoga sukses dalam belajar dan presentasi! 💪**

---

**© 2025 - Sistem Pemesanan Tiket Event Online**
**Dibuat dengan ❤️ untuk pembelajaran PBO**

---

## 📌 Last Updated

Project ini terakhir di-update: **17 Desember 2025**

**Version:** 1.0.0
**Status:** ✅ Completed & Ready for Use
**Tested On:** 
- Java 8+
- Windows 10/11
- Linux Ubuntu
- macOS

---

*[Kembali ke atas](#-index---sistem-pemesanan-tiket-event-online)*

