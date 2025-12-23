# Quick Start Guide 🚀

## Cara Cepat Menjalankan Aplikasi

### Untuk Windows:

1. **Compile Program:**
   ```bash
   .\compile.bat
   ```
   atau double-click file `compile.bat`

2. **Run Program:**
   ```bash
   .\run.bat
   ```
   atau double-click file `run.bat`

### Untuk Linux/Mac:

1. **Berikan permission untuk script:**
   ```bash
   chmod +x compile.sh run.sh
   ```

2. **Compile Program:**
   ```bash
   ./compile.sh
   ```

3. **Run Program:**
   ```bash
   ./run.sh
   ```

---

## 🎮 Cara Menggunakan Aplikasi

### Menu Utama
Saat pertama kali menjalankan, Anda akan melihat:
```
========== MENU UTAMA ==========
1. Login
2. Register
3. Keluar
================================
```

---

## 📝 Testing Scenario

### Scenario 1: Testing sebagai PEMBELI (Customer)

#### Step 1: Login
```
Menu: 1 (Login)
Username: pembeli1
Password: pembeli123
```

#### Step 2: Browse Event
```
Menu Pembeli: 1 (Browse Event)
(Lihat semua event yang tersedia)
```

#### Step 3: Pesan Tiket
```
Menu Pembeli: 3 (Pesan Tiket)
Masukkan Event ID: EVT001
Jumlah tiket: 2
Pilih metode pembayaran: 1 (Transfer Bank)
Konfirmasi: y
```

#### Step 4: Lihat Tiket Virtual
```
Menu Pembeli: 5 (Lihat Tiket Saya)
Pilih nomor tiket: 1
(Tiket virtual akan ditampilkan dengan barcode)
```

#### Step 5: Lihat Riwayat
```
Menu Pembeli: 4 (Riwayat Transaksi)
(Lihat semua transaksi yang pernah dilakukan)
```

#### Step 6: Logout
```
Menu Pembeli: 6 (Logout)
```

---

### Scenario 2: Testing sebagai PENJUAL (Event Organizer)

#### Step 1: Login
```
Menu: 1 (Login)
Username: organizer1
Password: organizer123
```

#### Step 2: Lihat Event Saya
```
Menu Penjual: 2 (Lihat Event Saya)
(Lihat event yang sudah dibuat)
```

#### Step 3: Tambah Event Baru
```
Menu Penjual: 1 (Tambah Event Baru)

Contoh Input:
Nama Event: Festival Musik Jazz 2025
Deskripsi: Festival musik jazz terbesar di Indonesia
Harga Tiket: 250000
Kuota Tiket: 1000
Lokasi: Jakarta Convention Center
Tanggal Event: 25-12-2025
Jam Event: 18:00
Kategori: Konser
URL Poster: https://example.com/poster-jazz.jpg
```

#### Step 4: Edit Event
```
Menu Penjual: 3 (Edit Event)
Event ID: EVT001
(Update informasi event)
```

#### Step 5: Lihat Statistik Penjualan
```
Menu Penjual: 5 (Lihat Statistik Penjualan)
(Lihat total event, tiket terjual, dan pendapatan)
```

#### Step 6: Logout
```
Menu Penjual: 6 (Logout)
```

---

### Scenario 3: Testing sebagai ADMIN

#### Step 1: Login
```
Menu: 1 (Login)
Username: admin
Password: admin123
```

#### Step 2: Verifikasi Event Pending
```
Menu Admin: 1 (Verifikasi Event Pending)
Pilih nomor event: 1
Setujui event? y
```

#### Step 3: Lihat Daftar User
```
Menu Admin: 2 (Kelola User)
(Lihat semua user yang terdaftar)
```

#### Step 4: Lihat Laporan Transaksi
```
Menu Admin: 3 (Lihat Laporan Transaksi)
(Lihat semua transaksi di sistem)
```

#### Step 5: Lihat Statistik Sistem
```
Menu Admin: 4 (Lihat Statistik Sistem)
(Lihat total user, event, dan transaksi)
```

#### Step 6: Logout
```
Menu Admin: 5 (Logout)
```

---

### Scenario 4: Registrasi User Baru

#### Register sebagai Pembeli:
```
Menu: 2 (Register)
Pilih role: 1 (Pembeli)

Username: customertest
Password: test123456
Email: customer@test.com
Alamat: Jl. Testing No. 123
Nomor Telepon: 081234567890
```

#### Register sebagai Penjual:
```
Menu: 2 (Register)
Pilih role: 2 (Penjual)

Username: organizertest
Password: test123456
Email: organizer@test.com
Nama Organisasi: Test Event Organizer
Nomor Telepon: 081234567890
```

---

## 🧪 Testing Exception Handling

### Test 1: Login dengan Password Salah
```
Username: admin
Password: salahpassword
Expected: ✗ Login gagal: Password salah!
```

### Test 2: Pesan Tiket Melebihi Kuota
```
Event ID: EVT001 (kuota tersisa: 50)
Jumlah tiket: 100
Expected: ✗ Tiket untuk event 'Konser Musik Rock 2025' tidak mencukupi! 
          Diminta: 100, Tersedia: 50
```

### Test 3: Event Tidak Ditemukan
```
Event ID: EVT999
Expected: ✗ Event dengan ID EVT999 tidak ditemukan!
```

### Test 4: Password Terlalu Pendek (Register)
```
Password: 123
Expected: ✗ Password minimal 6 karakter!
```

### Test 5: Username Sudah Digunakan
```
Username: admin (sudah ada)
Expected: ✗ Username sudah digunakan!
```

---

## 🎯 Fitur yang Dapat Didemonstrasikan

### 1. Encapsulation
- Coba lihat detail user/event → Data diakses via getter
- Edit event → Data diupdate via setter dengan validasi

### 2. Inheritance
- Login sebagai Admin/Penjual/Pembeli → Masing-masing mewarisi dari User

### 3. Abstraction
- Setiap role menampilkan menu berbeda → Implementation abstract method

### 4. Polymorphism
- `currentUser.displayMenu()` → Output berbeda sesuai tipe user

### 5. Interface
- Admin approve/reject event → Implementasi interface Verifikasi
- Pembeli proses pembayaran → Implementasi interface Pembayaran

### 6. Collection (ArrayList)
- Browse event → Data diambil dari ArrayList
- Lihat riwayat transaksi → Iterate ArrayList

### 7. Exception Handling
- Coba beli tiket > kuota → TiketHabisException
- Login salah → LoginGagalException
- Event tidak ada → EventTidakDitemukanException

---

## 💡 Tips Testing

1. **Mulai dari Admin** untuk approve event yang pending
2. **Gunakan Penjual** untuk membuat event baru
3. **Login sebagai Pembeli** untuk testing flow pembelian lengkap
4. **Test berbagai error case** untuk melihat exception handling
5. **Perhatikan perubahan kuota** tiket saat melakukan transaksi

---

## 🐛 Troubleshooting

### Program tidak compile:
```bash
# Pastikan Java JDK terinstall
java -version

# Compile ulang dengan verbose
javac -verbose -d bin src/**/*.java src/Main.java
```

### Program tidak jalan:
```bash
# Cek apakah file .class ada di folder bin
ls bin/

# Run dengan classpath explicit
java -cp bin Main
```

### Error encoding characters:
```bash
# Compile dengan UTF-8 encoding
javac -encoding UTF-8 -d bin src/**/*.java src/Main.java
```

---

## 📞 Support

Jika menemui masalah:
1. Baca error message dengan teliti
2. Cek DOKUMENTASI_OOP.md untuk penjelasan detail
3. Pastikan semua file ada di tempat yang benar
4. Verifikasi Java version >= 8

---

**Happy Testing! 🎉**

