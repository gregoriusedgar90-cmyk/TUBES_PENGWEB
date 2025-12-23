# Cara Compile dan Run Program Java

## ✅ Program Sudah Berhasil Dikompilasi!

Semua file `.class` sudah tersedia di folder `bin/`. Anda bisa langsung menjalankan program.

---

## 🚀 Cara Termudah: Menggunakan Script

### Untuk Windows:

**Run program:**
```bash
.\run.bat
```
atau double-click file `run.bat`

**Jika perlu compile ulang:**
```bash
.\compile.bat
```

### Untuk Linux/Mac:

**Berikan permission dulu (hanya sekali):**
```bash
chmod +x run.sh
```

**Run program:**
```bash
./run.sh
```

**Jika perlu compile ulang:**
```bash
./compile.sh
```

---

## 🔧 Cara Manual: Command Line

### Windows (PowerShell atau CMD):

**Compile (jika diperlukan):**
```powershell
# Buat folder bin jika belum ada
mkdir bin

# Compile semua file
javac -encoding UTF-8 -d bin src/exceptions/*.java src/interfaces/*.java src/models/*.java src/services/*.java src/Main.java
```

**Run:**
```powershell
java -cp bin Main
```

### Linux/Mac (Terminal):

**Compile (jika diperlukan):**
```bash
# Buat folder bin jika belum ada
mkdir -p bin

# Compile semua file
javac -d bin src/exceptions/*.java src/interfaces/*.java src/models/*.java src/services/*.java src/Main.java
```

**Run:**
```bash
java -cp bin Main
```

---

## 📋 Verifikasi Instalasi

**Cek versi Java:**
```bash
java -version
```

Harus menampilkan Java version 8 atau lebih tinggi.

**Cek versi Compiler:**
```bash
javac -version
```

Jika command tidak ditemukan, install Java JDK terlebih dahulu.

---

## 🎮 Setelah Program Berjalan

Anda akan melihat tampilan seperti ini:

```
╔═══════════════════════════════════════════════════════════╗
║                                                           ║
║        SISTEM PEMESANAN TIKET EVENT ONLINE                ║
║              Berbasis Object-Oriented Programming         ║
║                                                           ║
╚═══════════════════════════════════════════════════════════╝

========== MENU UTAMA ==========
1. Login
2. Register
3. Keluar
================================
Pilih menu: _
```

### Login dengan Akun Test:

**Admin:**
- Username: `admin`
- Password: `admin123`

**Penjual:**
- Username: `organizer1`
- Password: `organizer123`

**Pembeli:**
- Username: `pembeli1`
- Password: `pembeli123`

---

## 🐛 Troubleshooting

### Error: "javac tidak dikenali"

**Solusi:** Install Java JDK (bukan hanya JRE)
- Download dari: https://www.oracle.com/java/technologies/downloads/
- Atau install OpenJDK

### Error: Encoding characters tidak muncul dengan benar

**Windows:**
```powershell
chcp 65001
java -Dfile.encoding=UTF-8 -cp bin Main
```

**Linux/Mac:**
```bash
export LANG=en_US.UTF-8
java -cp bin Main
```

### Error: "Could not find or load main class Main"

**Pastikan Anda di folder root project:**
```bash
cd "D:\Kuliah\PBO TP\tubes"
```

**Pastikan folder bin ada dan berisi file .class:**
```bash
ls bin/
```

Jika folder bin kosong, compile ulang dengan script compile.

### Error: Class tidak ditemukan saat compile

**Compile dengan urutan yang benar:**
```bash
# 1. Compile exceptions dulu
javac -encoding UTF-8 -d bin src/exceptions/*.java

# 2. Compile interfaces
javac -encoding UTF-8 -d bin src/interfaces/*.java

# 3. Compile models
javac -encoding UTF-8 -cp bin -d bin src/models/*.java

# 4. Compile services
javac -encoding UTF-8 -cp bin -d bin src/services/*.java

# 5. Compile Main
javac -encoding UTF-8 -cp bin -d bin src/Main.java
```

---

## 📦 Struktur Folder Setelah Compile

```
tubes/
├── bin/                    ← Compiled files
│   ├── exceptions/
│   │   └── *.class
│   ├── interfaces/
│   │   └── *.class
│   ├── models/
│   │   └── *.class
│   ├── services/
│   │   └── *.class
│   └── Main.class
└── src/                    ← Source files
    ├── exceptions/
    ├── interfaces/
    ├── models/
    ├── services/
    └── Main.java
```

---

## 🎓 Tips untuk Presentasi/Demo

1. **Buka 2 Terminal:**
   - Terminal 1: Run sebagai Admin
   - Terminal 2: Run sebagai Pembeli
   - Demonstrasikan flow lengkap

2. **Skenario Demo:**
   - Terminal 1 (Admin): Approve event pending
   - Terminal 2 (Pembeli): Login → Browse → Beli tiket
   - Terminal 1 (Admin): Lihat laporan transaksi

3. **Tunjukkan Exception Handling:**
   - Coba login dengan password salah
   - Coba beli tiket melebihi kuota
   - Coba akses event yang tidak ada

4. **Tunjukkan Polymorphism:**
   - Login sebagai 3 role berbeda
   - Tunjukkan menu yang berbeda (displayMenu())

---

## 💡 Catatan Penting

- Program ini menggunakan **in-memory database** (ArrayList)
- Data akan hilang setiap kali program ditutup
- Untuk production, bisa integrasikan dengan database MySQL/PostgreSQL
- Scanner input menggunakan console, pastikan terminal support UTF-8

---

## ✨ Fitur yang Bisa Didemonstrasikan

✅ **Encapsulation** - Akses data via getter/setter
✅ **Inheritance** - User → Admin/Penjual/Pembeli
✅ **Abstraction** - Abstract class User
✅ **Polymorphism** - displayMenu() berbeda per role
✅ **Interface** - Verifikasi & Pembayaran
✅ **Collection** - ArrayList untuk database
✅ **Exception Handling** - Try-catch di berbagai flow
✅ **Singleton Pattern** - Database.getInstance()

---

**Selamat Mencoba! 🎉**

Jika ada pertanyaan atau masalah, silakan buka file:
- `README.md` untuk dokumentasi lengkap
- `DOKUMENTASI_OOP.md` untuk penjelasan detail OOP
- `QUICK_START.md` untuk panduan testing
- `STRUKTUR_PROJECT.txt` untuk overview struktur

