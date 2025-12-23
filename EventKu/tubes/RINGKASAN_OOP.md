# 📚 RINGKASAN PRINSIP OOP - Quick Reference

## Daftar Lengkap Implementasi OOP

### 1️⃣ ENCAPSULATION (Enkapsulasi)

**Definisi:** Menyembunyikan detail implementasi, data dibuat private dan diakses via getter/setter.

**File Implementation:**
```
✓ src/models/User.java
✓ src/models/Admin.java
✓ src/models/Penjual.java
✓ src/models/Pembeli.java
✓ src/models/Event.java
✓ src/models/Transaksi.java
✓ src/models/Tiket.java
```

**Contoh Kode:**
```java
// User.java
private String username;  // PRIVATE attribute

public String getUsername() {  // PUBLIC getter
    return username;
}

public void setUsername(String username) {  // PUBLIC setter dengan validasi
    if (username != null && !username.isEmpty()) {
        this.username = username;
    }
}
```

**Lihat di Line:**
- `src/models/User.java` line 11-16 (private attributes)
- `src/models/Event.java` line 14-29 (private attributes + validation)

---

### 2️⃣ INHERITANCE (Pewarisan)

**Definisi:** Class child mewarisi attributes dan methods dari class parent.

**Hierarchy:**
```
        User (Abstract Parent)
        /       |        \
       /        |         \
    Admin    Penjual    Pembeli
```

**File Implementation:**
```
Parent:  src/models/User.java
Children:
  ✓ src/models/Admin.java
  ✓ src/models/Penjual.java
  ✓ src/models/Pembeli.java
```

**Contoh Kode:**
```java
// User.java (Parent)
public abstract class User {
    private String userId;
    private String username;
    // ... common attributes
}

// Admin.java (Child)
public class Admin extends User {  // EXTENDS keyword
    private String adminLevel;  // Additional attribute
    
    public Admin(String userId, String username, ...) {
        super(userId, username, ...);  // Call parent constructor
        this.adminLevel = adminLevel;
    }
}
```

**Lihat di Line:**
- `src/models/Admin.java` line 17 (`extends User`)
- `src/models/Penjual.java` line 14 (`extends User`)
- `src/models/Pembeli.java` line 18 (`extends User`)

---

### 3️⃣ ABSTRACTION (Abstraksi)

**Definisi:** Menyembunyikan detail implementasi, hanya menampilkan fungsionalitas.

**File Implementation:**
```
Abstract Class: src/models/User.java
  ✓ public abstract void displayMenu();
  ✓ public abstract String getMenuOptions();

Implementations:
  ✓ src/models/Admin.java → Override displayMenu()
  ✓ src/models/Penjual.java → Override displayMenu()
  ✓ src/models/Pembeli.java → Override displayMenu()
```

**Contoh Kode:**
```java
// User.java
public abstract class User {  // ABSTRACT class
    public abstract void displayMenu();  // ABSTRACT method (no body)
}

// Admin.java
public class Admin extends User {
    @Override
    public void displayMenu() {  // MUST implement
        System.out.println("=== MENU ADMIN ===");
        // ... implementation
    }
}
```

**Lihat di Line:**
- `src/models/User.java` line 10 (`abstract class User`)
- `src/models/User.java` line 32-33 (abstract methods)
- `src/models/Admin.java` line 28-37 (implementation)

---

### 4️⃣ POLYMORPHISM (Polimorfisme)

**Definisi:** Satu interface/method menghasilkan behavior berbeda berdasarkan tipe object.

**File Implementation:**
```
Main Usage: src/Main.java
  ✓ User currentUser = authService.login(...);
  ✓ currentUser.displayMenu();  // Different output per role
```

**Contoh Kode:**
```java
// Main.java
User currentUser = authService.login(username, password);
// currentUser bisa: Admin, Penjual, atau Pembeli (Runtime Polymorphism)

currentUser.displayMenu();
// Output berbeda tergantung tipe sebenarnya:
// - Jika Admin   → Menu Admin
// - Jika Penjual → Menu Penjual  
// - Jika Pembeli → Menu Pembeli
```

**Output Examples:**

**Admin:**
```
========== MENU ADMIN ==========
1. Verifikasi Event Pending
2. Kelola User
3. Lihat Laporan Transaksi
4. Lihat Statistik Sistem
5. Logout
================================
```

**Penjual:**
```
========== MENU PENJUAL ==========
1. Tambah Event Baru
2. Lihat Event Saya
3. Edit Event
4. Hapus Event
5. Lihat Statistik Penjualan
6. Logout
====================================
```

**Pembeli:**
```
========== MENU PEMBELI ==========
1. Browse Event
2. Cari Event
3. Pesan Tiket
4. Riwayat Transaksi
5. Lihat Tiket Saya
6. Logout
==================================
```

**Lihat di Line:**
- `src/Main.java` line 32 (polymorphic call `displayMenu()`)
- `src/Main.java` line 34-42 (instanceof check & downcast)

---

### 5️⃣ INTERFACE

**Definisi:** Contract yang mendefinisikan method signature tanpa implementasi.

**File Implementation:**
```
Interfaces:
  ✓ src/interfaces/Verifikasi.java
    → Implemented by: Admin
  
  ✓ src/interfaces/Pembayaran.java
    → Implemented by: Pembeli
```

**Contoh Kode:**

**Interface Definition:**
```java
// Verifikasi.java
public interface Verifikasi {
    boolean verifikasi(String id);
    void approveVerifikasi(String id);
    void rejectVerifikasi(String id);
    String getStatusVerifikasi(String id);
}
```

**Interface Implementation:**
```java
// Admin.java
public class Admin extends User implements Verifikasi {
    
    @Override
    public boolean verifikasi(String eventId) {
        // Implementation
        return true;
    }
    
    @Override
    public void approveVerifikasi(String eventId) {
        // Implementation
    }
    // ... implement all methods
}
```

**Lihat di Line:**
- `src/interfaces/Verifikasi.java` line 9-13 (interface methods)
- `src/models/Admin.java` line 17 (`implements Verifikasi`)
- `src/models/Admin.java` line 40-62 (implementation)
- `src/interfaces/Pembayaran.java` line 9-13
- `src/models/Pembeli.java` line 18 (`implements Pembayaran`)

---

### 6️⃣ COLLECTION (ArrayList)

**Definisi:** Framework untuk menyimpan dan memanipulasi group of objects.

**File Implementation:**
```
✓ src/services/Database.java
  → ArrayList<User> users
  → ArrayList<Event> events
  → ArrayList<Transaksi> transaksis
  → ArrayList<Tiket> tikets
```

**Contoh Kode:**
```java
// Database.java
public class Database {
    // COLLECTION declarations
    private ArrayList<User> users;
    private ArrayList<Event> events;
    private ArrayList<Transaksi> transaksis;
    private ArrayList<Tiket> tikets;
    
    private Database() {
        users = new ArrayList<>();  // Initialize
        events = new ArrayList<>();
        transaksis = new ArrayList<>();
        tikets = new ArrayList<>();
    }
    
    // CRUD Operations
    public void addUser(User user) {
        users.add(user);  // ADD
    }
    
    public User getUserById(String userId) {
        for (User user : users) {  // SEARCH
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean deleteUser(String userId) {
        return users.removeIf(user ->   // DELETE
            user.getUserId().equals(userId));
    }
    
    public ArrayList<Event> getEventsByStatus(String status) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {  // FILTER
            if (event.getStatusVerifikasi().equals(status)) {
                result.add(event);
            }
        }
        return result;
    }
}
```

**Operations Used:**
- `add()` - Menambah data
- `get()` - Mengambil data by index
- `remove()` - Menghapus data
- `removeIf()` - Menghapus dengan kondisi
- `contains()` - Cek keberadaan
- Loop `for-each` - Iterasi semua data

**Lihat di Line:**
- `src/services/Database.java` line 20-23 (ArrayList declarations)
- `src/services/Database.java` line 62-65 (add operations)
- `src/services/Database.java` line 67-74 (search operation)
- `src/services/Database.java` line 81-83 (delete operation)

---

### 7️⃣ EXCEPTION HANDLING

**Definisi:** Mekanisme menangani runtime errors dengan try-catch.

**Custom Exceptions:**
```
✓ src/exceptions/LoginGagalException.java
✓ src/exceptions/TiketHabisException.java
✓ src/exceptions/EventTidakDitemukanException.java
✓ src/exceptions/PembayaranGagalException.java
```

**File Implementation:**
```
Throwing Exceptions:
  ✓ src/services/AuthService.java
  ✓ src/services/EventService.java
  ✓ src/services/TransaksiService.java

Catching Exceptions:
  ✓ src/Main.java
```

**Contoh Kode:**

**Custom Exception:**
```java
// TiketHabisException.java
public class TiketHabisException extends Exception {
    public TiketHabisException(String message) {
        super(message);
    }
    
    public TiketHabisException(String eventName, int requested, int available) {
        super(String.format(
            "Tiket untuk event '%s' tidak mencukupi! Diminta: %d, Tersedia: %d", 
            eventName, requested, available));
    }
}
```

**Throwing Exception:**
```java
// TransaksiService.java
public Transaksi createTransaksi(String pembeliId, String eventId, int jumlahTiket)
        throws TiketHabisException, EventTidakDitemukanException {
    
    Event event = database.getEventById(eventId);
    if (event == null) {
        throw new EventTidakDitemukanException(
            "Event dengan ID " + eventId + " tidak ditemukan!");
    }
    
    if (event.getKuotaTersisa() < jumlahTiket) {
        throw new TiketHabisException(
            event.getNamaEvent(), jumlahTiket, event.getKuotaTersisa());
    }
    
    // ... process
}
```

**Catching Exception:**
```java
// Main.java
try {
    Transaksi transaksi = transaksiService.createTransaksi(
        pembeli.getUserId(), eventId, jumlah);
    
    transaksiService.prosesPembayaran(
        transaksi.getTransaksiId(), metodePembayaran, kode);
        
} catch (TiketHabisException e) {
    System.out.println("✗ " + e.getMessage());
} catch (EventTidakDitemukanException e) {
    System.out.println("✗ " + e.getMessage());
} catch (PembayaranGagalException e) {
    System.out.println("✗ " + e.getMessage());
} catch (Exception e) {
    System.out.println("✗ Terjadi kesalahan: " + e.getMessage());
}
```

**Lihat di Line:**
- `src/exceptions/TiketHabisException.java` line 7-15
- `src/services/AuthService.java` line 29 (`throws LoginGagalException`)
- `src/services/TransaksiService.java` line 21 (`throws` multiple exceptions)
- `src/Main.java` line 467-487 (try-catch blocks)

---

## 🎁 BONUS: Design Pattern

### SINGLETON PATTERN

**File Implementation:**
```
✓ src/services/Database.java
```

**Contoh Kode:**
```java
public class Database {
    // SINGLETON: Static instance
    private static Database instance;
    
    // SINGLETON: Private constructor
    private Database() {
        users = new ArrayList<>();
        events = new ArrayList<>();
        transaksis = new ArrayList<>();
        tikets = new ArrayList<>();
    }

    // SINGLETON: Public accessor
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
```

**Usage:**
```java
// Di semua service classes
Database database = Database.getInstance();
// Semua service mengakses database instance yang sama
```

**Lihat di Line:**
- `src/services/Database.java` line 18 (static instance)
- `src/services/Database.java` line 26-32 (private constructor)
- `src/services/Database.java` line 35-40 (getInstance method)

---

## 📊 Tabel Summary

| No | Prinsip OOP | File Implementation | Line Reference | Complexity |
|----|-------------|-------------------|----------------|------------|
| 1 | Encapsulation | models/*.java | User.java:11-16 | ⭐⭐ |
| 2 | Inheritance | User → Admin/Penjual/Pembeli | Admin.java:17 | ⭐⭐⭐ |
| 3 | Abstraction | User (abstract class) | User.java:10,32-33 | ⭐⭐⭐ |
| 4 | Polymorphism | Main.java displayMenu() | Main.java:32 | ⭐⭐⭐⭐ |
| 5 | Interface | Verifikasi, Pembayaran | interfaces/*.java | ⭐⭐⭐ |
| 6 | Collection | ArrayList in Database | Database.java:20-23 | ⭐⭐ |
| 7 | Exception | Custom exceptions | exceptions/*.java | ⭐⭐⭐ |
| 8 | Singleton | Database pattern | Database.java:35-40 | ⭐⭐⭐⭐ |

---

## 🎯 Checklist untuk Presentasi

### Persiapan:
- [ ] Compile program berhasil (no errors)
- [ ] Bisa run dan tampil menu utama
- [ ] Test login 3 role berbeda
- [ ] Test satu flow lengkap (browse → pesan → bayar)

### Demo OOP Concepts:

**Encapsulation:**
- [ ] Tunjukkan private attributes di User.java
- [ ] Tunjukkan getter/setter dengan validasi
- [ ] Jelaskan kenapa tidak bisa akses langsung

**Inheritance:**
- [ ] Tunjukkan hierarchy User → Admin/Penjual/Pembeli
- [ ] Tunjukkan super() call di constructor
- [ ] Jelaskan attributes yang diwarisi

**Abstraction:**
- [ ] Tunjukkan abstract class User
- [ ] Tunjukkan abstract method displayMenu()
- [ ] Jelaskan kenapa setiap child harus implement

**Polymorphism:**
- [ ] Login sebagai 3 role berbeda
- [ ] Tunjukkan currentUser.displayMenu() → output berbeda
- [ ] Jelaskan runtime polymorphism

**Interface:**
- [ ] Tunjukkan interface Verifikasi & Pembayaran
- [ ] Tunjukkan implementasi di Admin & Pembeli
- [ ] Jelaskan contract-based programming

**Collection:**
- [ ] Tunjukkan ArrayList declarations di Database
- [ ] Tunjukkan add, search, filter operations
- [ ] Jelaskan keuntungan ArrayList vs array biasa

**Exception Handling:**
- [ ] Coba login password salah → LoginGagalException
- [ ] Coba beli tiket > kuota → TiketHabisException
- [ ] Tunjukkan try-catch block di Main.java
- [ ] Jelaskan graceful error handling

**Singleton:**
- [ ] Tunjukkan Database singleton pattern
- [ ] Jelaskan kenapa hanya 1 instance
- [ ] Tunjukkan getInstance() usage

---

## 📖 Referensi File

**Dokumentasi Lengkap:**
- `README.md` - Overview dan fitur lengkap
- `DOKUMENTASI_OOP.md` - Penjelasan detail setiap prinsip
- `QUICK_START.md` - Panduan testing cepat
- `STRUKTUR_PROJECT.txt` - Struktur dan alur kerja
- `CARA_COMPILE_DAN_RUN.md` - Panduan compile dan run

**Source Code:**
- `src/models/` - Domain models (7 files)
- `src/interfaces/` - Interface definitions (2 files)
- `src/services/` - Business logic (4 files)
- `src/exceptions/` - Custom exceptions (4 files)
- `src/Main.java` - Entry point

**Scripts:**
- `compile.bat` / `compile.sh` - Compile script
- `run.bat` / `run.sh` - Run script

---

## 💡 Tips Presentasi

1. **Mulai dari konsep dasar** → tinggi (Encapsulation → Polymorphism)
2. **Tunjukkan kode sambil explain** konsep OOP
3. **Demo langsung** di terminal untuk setiap fitur
4. **Jelaskan manfaat** setiap prinsip OOP
5. **Siap jawab pertanyaan** tentang implementasi

**Pertanyaan yang Mungkin Muncul:**
- Q: "Kenapa pakai abstract class, bukan concrete class?"
  A: Karena User tidak pernah diinstansiasi langsung, selalu Admin/Penjual/Pembeli

- Q: "Kenapa pakai interface, tidak method biasa di class?"
  A: Interface memaksa contract, mendukung multiple inheritance

- Q: "Kenapa pakai ArrayList, bukan array biasa?"
  A: ArrayList dynamic size, built-in methods, type-safe dengan generics

- Q: "Kenapa bikin custom exception?"
  A: Untuk specific error handling yang lebih meaningful

---

**Semoga Sukses Presentasinya! 🎉**

---

**© 2025 - Quick Reference untuk Sistem Pemesanan Tiket Event**

