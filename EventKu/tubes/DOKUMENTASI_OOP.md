# Dokumentasi Lengkap Prinsip OOP

## 📋 Daftar Isi
1. [Encapsulation](#1-encapsulation)
2. [Inheritance](#2-inheritance)
3. [Abstraction](#3-abstraction)
4. [Polymorphism](#4-polymorphism)
5. [Interface](#5-interface)
6. [Collection](#6-collection)
7. [Exception Handling](#7-exception-handling)

---

## 1. ENCAPSULATION

### Definisi
Enkapsulasi adalah prinsip menyembunyikan detail implementasi dan hanya menampilkan fungsi yang diperlukan. Data (attributes) dibuat private dan diakses melalui public methods (getter/setter).

### Implementasi dalam Project

#### File: `src/models/User.java`
```java
public abstract class User {
    // ENCAPSULATION: Private attributes
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean isActive;

    // Public Getter
    public String getUsername() {
        return username;
    }

    // Public Setter dengan Validasi
    public void setUsername(String username) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
    }

    // Setter dengan validasi minimal panjang
    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        }
    }
}
```

#### Manfaat:
- ✅ Data terlindungi dari akses langsung
- ✅ Validasi data dilakukan di setter
- ✅ Perubahan implementasi internal tidak mempengaruhi kode luar
- ✅ Kontrol akses yang lebih baik

#### File Lain yang Menerapkan:
- `src/models/Event.java`
- `src/models/Transaksi.java`
- `src/models/Tiket.java`
- `src/models/Penjual.java`
- `src/models/Pembeli.java`

---

## 2. INHERITANCE

### Definisi
Inheritance adalah mekanisme dimana suatu class dapat mewarisi attributes dan methods dari class lain. Class yang mewarisi disebut child class (subclass), yang diwarisi disebut parent class (superclass).

### Implementasi dalam Project

#### Hierarchy:
```
        User (Abstract Parent)
        /       |        \
       /        |         \
    Admin    Penjual    Pembeli
```

#### File: `src/models/User.java` (Parent)
```java
public abstract class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
    
    // Constructor
    public User(String userId, String username, String password, 
                String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    
    public abstract void displayMenu();
    public abstract String getMenuOptions();
}
```

#### File: `src/models/Admin.java` (Child)
```java
// INHERITANCE: Admin mewarisi dari User
public class Admin extends User {
    // Attribute tambahan khusus Admin
    private String adminLevel;
    
    // Constructor memanggil parent constructor
    public Admin(String userId, String username, String password, 
                 String email, String adminLevel) {
        super(userId, username, password, email, "Admin");
        this.adminLevel = adminLevel;
    }
    
    // Admin mewarisi semua method public dari User
    // Dan bisa mengakses melalui getter
    public void printInfo() {
        System.out.println(getUsername()); // Method dari parent
        System.out.println(getEmail());    // Method dari parent
    }
}
```

#### File: `src/models/Penjual.java` (Child)
```java
public class Penjual extends User {
    private String namaOrganisasi;
    private String nomorTelepon;
    private double totalPendapatan;
    
    public Penjual(String userId, String username, String password, 
                   String email, String namaOrganisasi, String nomorTelepon) {
        super(userId, username, password, email, "Penjual");
        this.namaOrganisasi = namaOrganisasi;
        this.nomorTelepon = nomorTelepon;
        this.totalPendapatan = 0.0;
    }
}
```

#### File: `src/models/Pembeli.java` (Child)
```java
public class Pembeli extends User {
    private String alamat;
    private String nomorTelepon;
    
    public Pembeli(String userId, String username, String password, 
                   String email, String alamat, String nomorTelepon) {
        super(userId, username, password, email, "Pembeli");
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }
}
```

#### Manfaat:
- ✅ Code reusability - tidak perlu menulis ulang attribute/method yang sama
- ✅ Hierarchical classification - organisasi class yang terstruktur
- ✅ Extensibility - mudah menambah child class baru

---

## 3. ABSTRACTION

### Definisi
Abstraction adalah proses menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas kepada user. Dalam Java, abstraction dicapai melalui abstract class dan interface.

### Implementasi dalam Project

#### File: `src/models/User.java`
```java
// ABSTRACTION: Abstract class tidak bisa diinstansiasi langsung
public abstract class User {
    private String userId;
    private String username;
    
    // Abstract method - tidak memiliki body
    // Setiap child class WAJIB mengimplementasikannya
    public abstract void displayMenu();
    public abstract String getMenuOptions();
    
    // Concrete method - bisa digunakan langsung oleh child
    public String getUsername() {
        return username;
    }
}
```

#### Implementasi di Child Classes:

**Admin.java:**
```java
public class Admin extends User {
    @Override
    public void displayMenu() {
        System.out.println("========== MENU ADMIN ==========");
        System.out.println("1. Verifikasi Event");
        System.out.println("2. Kelola User");
        System.out.println("3. Lihat Laporan");
        // ... menu admin
    }
    
    @Override
    public String getMenuOptions() {
        return "1-5";
    }
}
```

**Penjual.java:**
```java
public class Penjual extends User {
    @Override
    public void displayMenu() {
        System.out.println("========== MENU PENJUAL ==========");
        System.out.println("1. Tambah Event");
        System.out.println("2. Lihat Event Saya");
        System.out.println("3. Edit Event");
        // ... menu penjual
    }
    
    @Override
    public String getMenuOptions() {
        return "1-6";
    }
}
```

**Pembeli.java:**
```java
public class Pembeli extends User {
    @Override
    public void displayMenu() {
        System.out.println("========== MENU PEMBELI ==========");
        System.out.println("1. Browse Event");
        System.out.println("2. Cari Event");
        System.out.println("3. Pesan Tiket");
        // ... menu pembeli
    }
    
    @Override
    public String getMenuOptions() {
        return "1-6";
    }
}
```

#### Manfaat:
- ✅ Menyembunyikan kompleksitas implementasi
- ✅ Memaksa child class mengimplementasikan method tertentu
- ✅ Mendefinisikan contract yang harus diikuti
- ✅ Code yang lebih maintainable

---

## 4. POLYMORPHISM

### Definisi
Polymorphism berarti "banyak bentuk". Dalam OOP, polymorphism memungkinkan satu interface untuk digunakan oleh berbagai tipe data yang berbeda.

### Implementasi dalam Project

#### File: `src/Main.java`
```java
public class Main {
    public static void main(String[] args) {
        // Login user
        User currentUser = authService.login(username, password);
        
        // POLYMORPHISM: currentUser bisa berupa Admin, Penjual, atau Pembeli
        // Method displayMenu() akan dipanggil sesuai tipe sebenarnya
        currentUser.displayMenu();
        
        // Runtime Polymorphism - Java menentukan method mana yang dipanggil
        // berdasarkan object sebenarnya, bukan tipe variabel
        
        if (currentUser instanceof Admin) {
            // Downcast ke Admin untuk akses method khusus Admin
            Admin admin = (Admin) currentUser;
            admin.approveVerifikasi("EVT001");
        } else if (currentUser instanceof Penjual) {
            Penjual penjual = (Penjual) currentUser;
            penjual.tambahEventId("EVT002");
        } else if (currentUser instanceof Pembeli) {
            Pembeli pembeli = (Pembeli) currentUser;
            pembeli.tambahRiwayatTransaksi("TRX001");
        }
    }
}
```

#### Contoh Output Berbeda:

**Jika login sebagai Admin:**
```
========== MENU ADMIN ==========
1. Verifikasi Event Pending
2. Kelola User (View/Edit/Delete)
3. Lihat Laporan Transaksi
4. Lihat Statistik Sistem
5. Logout
================================
```

**Jika login sebagai Penjual:**
```
========== MENU PENJUAL (EVENT ORGANIZER) ==========
1. Tambah Event Baru
2. Lihat Event Saya
3. Edit Event
4. Hapus Event
5. Lihat Statistik Penjualan
6. Logout
====================================================
```

**Jika login sebagai Pembeli:**
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

#### Method Overloading (Compile-time Polymorphism):
```java
// Di class Pembeli
public double hitungTotal(int jumlahTiket, double hargaTiket) {
    return jumlahTiket * hargaTiket;
}

// Overload dengan diskon
public double hitungTotal(int jumlahTiket, double hargaTiket, double diskon) {
    double total = jumlahTiket * hargaTiket;
    return total - (total * diskon / 100);
}
```

#### Manfaat:
- ✅ Satu interface, banyak implementasi
- ✅ Code yang lebih fleksibel dan extensible
- ✅ Mengurangi kompleksitas code

---

## 5. INTERFACE

### Definisi
Interface adalah contract yang mendefinisikan method-method yang harus diimplementasikan oleh class yang mengimplementasinya. Interface tidak memiliki implementasi method (hanya signature).

### Implementasi dalam Project

#### File: `src/interfaces/Verifikasi.java`
```java
public interface Verifikasi {
    boolean verifikasi(String id);
    void approveVerifikasi(String id);
    void rejectVerifikasi(String id);
    String getStatusVerifikasi(String id);
}
```

#### File: `src/interfaces/Pembayaran.java`
```java
public interface Pembayaran {
    boolean prosesPembayaran(String transaksiId, String metodePembayaran);
    double hitungTotal(int jumlahTiket, double hargaTiket);
    String generateKodePembayaran();
    boolean validasiPembayaran(String kodeVerifikasi);
}
```

#### Implementasi Interface di Class:

**Admin implements Verifikasi:**
```java
public class Admin extends User implements Verifikasi {
    
    @Override
    public boolean verifikasi(String eventId) {
        System.out.println("[Admin] Memverifikasi event: " + eventId);
        return true;
    }

    @Override
    public void approveVerifikasi(String eventId) {
        System.out.println("[Admin] Event " + eventId + " disetujui!");
    }

    @Override
    public void rejectVerifikasi(String eventId) {
        System.out.println("[Admin] Event " + eventId + " ditolak!");
    }

    @Override
    public String getStatusVerifikasi(String eventId) {
        return "Status verifikasi untuk event: " + eventId;
    }
}
```

**Pembeli implements Pembayaran:**
```java
public class Pembeli extends User implements Pembayaran {
    
    @Override
    public boolean prosesPembayaran(String transaksiId, String metodePembayaran) {
        System.out.println("Memproses pembayaran...");
        // Simulasi proses
        return true;
    }

    @Override
    public double hitungTotal(int jumlahTiket, double hargaTiket) {
        return jumlahTiket * hargaTiket;
    }

    @Override
    public String generateKodePembayaran() {
        return "PAY" + System.currentTimeMillis();
    }

    @Override
    public boolean validasiPembayaran(String kodeVerifikasi) {
        return kodeVerifikasi != null && kodeVerifikasi.startsWith("PAY");
    }
}
```

#### Penggunaan di Main:
```java
// Polymorphism dengan Interface
Verifikasi verifikator = new Admin(...);
verifikator.approveVerifikasi("EVT001");

Pembayaran processor = new Pembeli(...);
String kode = processor.generateKodePembayaran();
processor.prosesPembayaran("TRX001", "Transfer Bank");
```

#### Manfaat:
- ✅ Multiple inheritance (class bisa implements banyak interface)
- ✅ Loose coupling
- ✅ Contract-based programming
- ✅ Testability yang lebih baik

---

## 6. COLLECTION

### Definisi
Collection Framework adalah arsitektur untuk menyimpan dan memanipulasi group of objects. ArrayList adalah salah satu implementasi dari List interface.

### Implementasi dalam Project

#### File: `src/services/Database.java`
```java
public class Database {
    // COLLECTION: ArrayList untuk simulasi database
    private ArrayList<User> users;
    private ArrayList<Event> events;
    private ArrayList<Transaksi> transaksis;
    private ArrayList<Tiket> tikets;

    private Database() {
        // Inisialisasi collections
        users = new ArrayList<>();
        events = new ArrayList<>();
        transaksis = new ArrayList<>();
        tikets = new ArrayList<>();
    }

    // ===== USER MANAGEMENT =====
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(users); // Return copy untuk enkapsulasi
    }

    public boolean deleteUser(String userId) {
        return users.removeIf(user -> user.getUserId().equals(userId));
    }

    // ===== EVENT MANAGEMENT =====
    public void addEvent(Event event) {
        events.add(event);
    }

    public ArrayList<Event> getEventsByStatus(String status) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getStatusVerifikasi().equals(status)) {
                result.add(event);
            }
        }
        return result;
    }

    public ArrayList<Event> searchEvents(String keyword) {
        ArrayList<Event> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Event event : events) {
            if (event.getNamaEvent().toLowerCase().contains(lowerKeyword)) {
                result.add(event);
            }
        }
        return result;
    }
}
```

#### Operasi Collection yang Digunakan:

1. **Add (Menambah):**
```java
users.add(newUser);
events.add(newEvent);
```

2. **Search/Find (Mencari):**
```java
for (User user : users) {
    if (user.getUserId().equals(userId)) {
        return user;
    }
}
```

3. **Filter (Menyaring):**
```java
ArrayList<Event> approvedEvents = new ArrayList<>();
for (Event event : events) {
    if (event.getStatusVerifikasi().equals("Approved")) {
        approvedEvents.add(event);
    }
}
```

4. **Remove (Menghapus):**
```java
users.removeIf(user -> user.getUserId().equals(userId));
```

5. **Iterate (Iterasi):**
```java
for (Transaksi transaksi : transaksis) {
    System.out.println(transaksi.toString());
}
```

#### File Lain yang Menggunakan Collection:
- `src/models/Penjual.java` - ArrayList untuk eventIds
- `src/models/Pembeli.java` - ArrayList untuk riwayatTransaksiIds
- `src/services/EventService.java`
- `src/services/TransaksiService.java`

#### Manfaat:
- ✅ Dynamic size (tidak perlu tentukan ukuran di awal)
- ✅ Built-in methods untuk operasi umum
- ✅ Type-safe dengan Generics
- ✅ Simulasi database yang efektif

---

## 7. EXCEPTION HANDLING

### Definisi
Exception Handling adalah mekanisme untuk menangani runtime errors agar program tidak crash dan memberikan feedback yang informatif kepada user.

### Custom Exceptions dalam Project

#### 1. TiketHabisException
**File: `src/exceptions/TiketHabisException.java`**
```java
public class TiketHabisException extends Exception {
    public TiketHabisException(String message) {
        super(message);
    }
    
    public TiketHabisException(String eventName, int requested, int available) {
        super(String.format("Tiket untuk event '%s' tidak mencukupi! " +
                           "Diminta: %d, Tersedia: %d", 
                           eventName, requested, available));
    }
}
```

#### 2. LoginGagalException
**File: `src/exceptions/LoginGagalException.java`**
```java
public class LoginGagalException extends Exception {
    public LoginGagalException(String message) {
        super(message);
    }
}
```

#### 3. EventTidakDitemukanException
**File: `src/exceptions/EventTidakDitemukanException.java`**
```java
public class EventTidakDitemukanException extends Exception {
    public EventTidakDitemukanException(String message) {
        super(message);
    }
}
```

#### 4. PembayaranGagalException
**File: `src/exceptions/PembayaranGagalException.java`**
```java
public class PembayaranGagalException extends Exception {
    public PembayaranGagalException(String message) {
        super(message);
    }
}
```

### Implementasi Try-Catch

#### Di AuthService (Login):
**File: `src/services/AuthService.java`**
```java
public User login(String username, String password) throws LoginGagalException {
    try {
        // Validasi input
        if (username == null || username.isEmpty()) {
            throw new LoginGagalException("Username tidak boleh kosong!");
        }
        if (password == null || password.isEmpty()) {
            throw new LoginGagalException("Password tidak boleh kosong!");
        }

        // Cari user
        User user = database.getUserByUsername(username);
        
        if (user == null) {
            throw new LoginGagalException("Username tidak ditemukan!");
        }

        // Validasi password
        if (!user.getPassword().equals(password)) {
            throw new LoginGagalException("Password salah!");
        }

        // Login berhasil
        return user;

    } catch (LoginGagalException e) {
        System.out.println("✗ Login gagal: " + e.getMessage());
        throw e;
    }
}
```

#### Di TransaksiService (Create Transaksi):
**File: `src/services/TransaksiService.java`**
```java
public Transaksi createTransaksi(String pembeliId, String eventId, int jumlahTiket)
        throws TiketHabisException, EventTidakDitemukanException {
    
    // Validasi event
    Event event = database.getEventById(eventId);
    if (event == null) {
        throw new EventTidakDitemukanException(
            "Event dengan ID " + eventId + " tidak ditemukan!");
    }

    // Validasi kuota
    if (event.getKuotaTersisa() < jumlahTiket) {
        throw new TiketHabisException(
            event.getNamaEvent(), jumlahTiket, event.getKuotaTersisa());
    }

    // Proses transaksi...
}
```

#### Di Main (Handling Multiple Exceptions):
**File: `src/Main.java`**
```java
private static void handlePesanTiket(Pembeli pembeli) {
    try {
        System.out.print("Masukkan Event ID: ");
        String eventId = scanner.nextLine();
        
        System.out.print("Jumlah tiket: ");
        int jumlah = Integer.parseInt(scanner.nextLine());
        
        // Bisa throw TiketHabisException atau EventTidakDitemukanException
        Transaksi transaksi = transaksiService.createTransaksi(
            pembeli.getUserId(), eventId, jumlah);
        
        // Proses pembayaran - bisa throw PembayaranGagalException
        transaksiService.prosesPembayaran(
            transaksi.getTransaksiId(), metodePembayaran, kode);
        
    } catch (TiketHabisException e) {
        System.out.println("✗ " + e.getMessage());
        // Handle tiket habis
    } catch (EventTidakDitemukanException e) {
        System.out.println("✗ " + e.getMessage());
        // Handle event tidak ditemukan
    } catch (PembayaranGagalException e) {
        System.out.println("✗ " + e.getMessage());
        // Handle pembayaran gagal
    } catch (NumberFormatException e) {
        System.out.println("✗ Input harus berupa angka!");
        // Handle input format error
    } catch (Exception e) {
        System.out.println("✗ Terjadi kesalahan: " + e.getMessage());
        // Handle unexpected errors
    }
}
```

### Exception Flow Diagram

```
User Input
    ↓
Try Block
    ↓
Business Logic
    ↓
Validation
    ↓
[Error?] → Yes → Throw Exception → Catch Block → Handle Error → User Feedback
    ↓ No
Continue Process
    ↓
Success
```

### Contoh Skenario Exception:

#### Skenario 1: Tiket Habis
```
Input: 
- Event ID: EVT001
- Jumlah: 100
- Kuota tersisa: 50

Flow:
1. createTransaksi() dipanggil
2. Cek kuota: 50 < 100
3. throw new TiketHabisException(...)
4. catch block menangkap
5. Tampilkan pesan: "Tiket tidak mencukupi! Diminta: 100, Tersedia: 50"
6. Kuota tidak berkurang
```

#### Skenario 2: Login Gagal
```
Input:
- Username: admin
- Password: salahpassword

Flow:
1. login() dipanggil
2. User ditemukan
3. Validasi password: tidak cocok
4. throw new LoginGagalException("Password salah!")
5. catch block menangkap
6. Tampilkan pesan: "✗ Login gagal: Password salah!"
7. User tidak login
```

#### Skenario 3: Event Tidak Ditemukan
```
Input:
- Event ID: EVT999 (tidak ada)

Flow:
1. getEventById() dipanggil
2. Loop semua events: tidak ada yang cocok
3. return null
4. if (event == null)
5. throw new EventTidakDitemukanException(...)
6. catch block menangkap
7. Tampilkan pesan error
```

### Manfaat Exception Handling:
- ✅ Program tidak crash saat error
- ✅ User mendapat feedback yang jelas
- ✅ Error handling yang terstruktur
- ✅ Memisahkan error handling dari business logic
- ✅ Mudah di-debug dan di-maintain

---

## 🎯 Bonus: Design Pattern

### Singleton Pattern
**File: `src/services/Database.java`**

```java
public class Database {
    // SINGLETON: Instance tunggal
    private static Database instance;
    
    // Private constructor - tidak bisa diinstansiasi dari luar
    private Database() {
        users = new ArrayList<>();
        events = new ArrayList<>();
        transaksis = new ArrayList<>();
        tikets = new ArrayList<>();
    }

    // Public method untuk mendapatkan instance
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
```

**Penggunaan:**
```java
// Di semua service classes
Database database = Database.getInstance();

// Semua service mengakses database yang sama
// Tidak ada duplikasi data
```

**Manfaat:**
- ✅ Hanya satu instance database di seluruh aplikasi
- ✅ Global access point
- ✅ Konsistensi data
- ✅ Hemat memory

---

## 📊 Summary Implementasi OOP

| Prinsip OOP | Jumlah Implementasi | File Utama |
|-------------|---------------------|------------|
| Encapsulation | 7 classes | Semua models/*.java |
| Inheritance | 3 child classes | Admin, Penjual, Pembeli |
| Abstraction | 1 abstract class | User.java |
| Polymorphism | 3 overrides | displayMenu() di setiap child |
| Interface | 2 interfaces, 2 implementor | Verifikasi, Pembayaran |
| Collection | 4 ArrayLists | Database.java |
| Exception | 4 custom exceptions | exceptions/*.java |
| Singleton | 1 implementation | Database.java |

---

**© 2025 - Dokumentasi OOP untuk Sistem Pemesanan Tiket Event**

