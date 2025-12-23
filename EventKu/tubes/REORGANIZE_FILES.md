# 📁 Panduan Reorganisasi File

## ⚠️ Masalah

File-file masih ada di struktur console version (lama), perlu dipindahkan ke struktur Spring Boot (baru).

---

## 🔄 File yang Perlu Dipindahkan

### 1. Exceptions
**Dari:** `src/exceptions/*.java`  
**Ke:** `src/main/java/com/eventku/exceptions/`

```
src/exceptions/EventTidakDitemukanException.java 
  → src/main/java/com/eventku/exceptions/EventTidakDitemukanException.java

src/exceptions/LoginGagalException.java
  → src/main/java/com/eventku/exceptions/LoginGagalException.java

src/exceptions/PembayaranGagalException.java
  → src/main/java/com/eventku/exceptions/PembayaranGagalException.java

src/exceptions/TiketHabisException.java
  → src/main/java/com/eventku/exceptions/TiketHabisException.java
```

### 2. Models
**Dari:** `src/models/*.java`  
**Ke:** `src/main/java/com/eventku/models/`

```
src/models/User.java → src/main/java/com/eventku/models/User.java
src/models/Admin.java → src/main/java/com/eventku/models/Admin.java
src/models/Penjual.java → src/main/java/com/eventku/models/Penjual.java
src/models/Pembeli.java → src/main/java/com/eventku/models/Pembeli.java
src/models/Event.java → src/main/java/com/eventku/models/Event.java
src/models/Transaksi.java → src/main/java/com/eventku/models/Transaksi.java
src/models/Tiket.java → src/main/java/com/eventku/models/Tiket.java
```

### 3. Services
**Dari:** `src/services/*.java`  
**Ke:** `src/main/java/com/eventku/services/`

```
src/services/Database.java → src/main/java/com/eventku/services/Database.java
src/services/AuthService.java → src/main/java/com/eventku/services/AuthService.java
src/services/EventService.java → src/main/java/com/eventku/services/EventService.java
src/services/TransaksiService.java → src/main/java/com/eventku/services/TransaksiService.java
```

### 4. Interfaces
**Dari:** `src/interfaces/*.java`  
**Ke:** `src/main/java/com/eventku/interfaces/`

```
src/interfaces/Verifikasi.java → src/main/java/com/eventku/interfaces/Verifikasi.java
src/interfaces/Pembayaran.java → src/main/java/com/eventku/interfaces/Pembayaran.java
```

---

## ✏️ Update Package Statement

Setelah dipindahkan, **ubah package statement** di setiap file!

### Contoh: EventTidakDitemukanException.java

**Before:**
```java
package exceptions;  // ❌ Salah!

public class EventTidakDitemukanException extends Exception {
    // ...
}
```

**After:**
```java
package com.eventku.exceptions;  // ✅ Benar!

public class EventTidakDitemukanException extends Exception {
    // ...
}
```

### Contoh: User.java

**Before:**
```java
package models;  // ❌ Salah!

public abstract class User {
    // ...
}
```

**After:**
```java
package com.eventku.models;  // ✅ Benar!

public abstract class User {
    // ...
}
```

### Update Import Statements

File yang menggunakan class lain juga perlu diupdate import-nya:

**Before:**
```java
import models.User;
import services.Database;
import exceptions.LoginGagalException;
```

**After:**
```java
import com.eventku.models.User;
import com.eventku.services.Database;
import com.eventku.exceptions.LoginGagalException;
```

---

## 🚀 Cara Cepat (Windows)

### Step 1: Buat Folder
```powershell
cd "D:\Kuliah\PBO TP\tubes\src\main\java\com\eventku"

# Buat folder jika belum ada
mkdir exceptions
mkdir models
mkdir services
mkdir interfaces
```

### Step 2: Copy Files
```powershell
# Copy exceptions
Copy-Item "..\..\..\..\exceptions\*.java" "exceptions\"

# Copy models
Copy-Item "..\..\..\..\models\*.java" "models\"

# Copy services
Copy-Item "..\..\..\..\services\*.java" "services\"

# Copy interfaces
Copy-Item "..\..\..\..\interfaces\*.java" "interfaces\"
```

### Step 3: Update Package Statements

Buka setiap file dan ubah:
- `package exceptions;` → `package com.eventku.exceptions;`
- `package models;` → `package com.eventku.models;`
- `package services;` → `package com.eventku.services;`
- `package interfaces;` → `package com.eventku.interfaces;`

---

## 🧹 Cleanup (Optional)

Setelah yakin semua bekerja, hapus folder lama:
```powershell
# HATI-HATI! Backup dulu sebelum delete
Remove-Item "src\exceptions" -Recurse
Remove-Item "src\models" -Recurse
Remove-Item "src\services" -Recurse
Remove-Item "src\interfaces" -Recurse
```

---

## ✅ Struktur Akhir yang Benar

```
tubes/
├── src/main/java/com/eventku/
│   ├── Application.java
│   │
│   ├── controller/
│   │   ├── AdminController.java
│   │   ├── AuthController.java          ← LOGIN & REGISTER ada di sini!
│   │   ├── EventController.java
│   │   ├── QRCodeController.java
│   │   └── TransaksiController.java
│   │
│   ├── dto/
│   │   ├── ApiResponse.java
│   │   ├── LoginRequest.java            ← Request untuk login
│   │   └── RegisterRequest.java         ← Request untuk register
│   │
│   ├── exceptions/                       ← Pindahkan ke sini!
│   │   ├── EventTidakDitemukanException.java
│   │   ├── LoginGagalException.java
│   │   ├── PembayaranGagalException.java
│   │   └── TiketHabisException.java
│   │
│   ├── interfaces/                       ← Pindahkan ke sini!
│   │   ├── Pembayaran.java
│   │   └── Verifikasi.java
│   │
│   ├── models/                           ← Pindahkan ke sini!
│   │   ├── Admin.java
│   │   ├── Event.java
│   │   ├── Pembeli.java
│   │   ├── Penjual.java
│   │   ├── Tiket.java
│   │   ├── Transaksi.java
│   │   └── User.java
│   │
│   ├── services/                         ← Pindahkan ke sini!
│   │   ├── AuthService.java             ← Service untuk login/register
│   │   ├── Database.java
│   │   ├── EventService.java
│   │   └── TransaksiService.java
│   │
│   └── util/
│       └── QRCodeGenerator.java
│
└── frontend/
    ├── login.html                        ← Halaman login
    ├── register.html                     ← Halaman register
    └── ...
```

---

## 📍 Dimana Login & Register?

### Backend (Java):
1. **Controller:** `src/main/java/com/eventku/controller/AuthController.java`
   - Endpoint: `POST /api/auth/login`
   - Endpoint: `POST /api/auth/register`

2. **Service:** `src/main/java/com/eventku/services/AuthService.java`
   - Method: `login(username, password)`
   - Method: `register(...)`

3. **DTO:** 
   - `src/main/java/com/eventku/dto/LoginRequest.java`
   - `src/main/java/com/eventku/dto/RegisterRequest.java`

### Frontend (HTML/JS):
1. **Halaman Login:** `frontend/login.html`
2. **Halaman Register:** `frontend/register.html`
3. **Logic:** `frontend/js/auth.js`

---

## 🔍 Apa itu Exception?

Exception (di folder `exceptions/`) **bukan fitur**, tapi **error handling**:

- `LoginGagalException` - **Error** ketika login gagal
- `EventTidakDitemukanException` - **Error** ketika event tidak ada
- `TiketHabisException` - **Error** ketika tiket habis
- `PembayaranGagalException` - **Error** ketika pembayaran gagal

**Login dan Register sendiri** adalah **fitur** di:
- `AuthController.java` (Backend)
- `login.html` & `register.html` (Frontend)

---

## 🎯 Checklist

- [ ] Buat folder di `src/main/java/com/eventku/`
- [ ] Copy file exceptions
- [ ] Copy file models
- [ ] Copy file services
- [ ] Copy file interfaces
- [ ] Update package statements (semua file!)
- [ ] Update import statements
- [ ] Rebuild: `mvn clean package`
- [ ] Test compile berhasil
- [ ] Hapus folder lama (optional)

---

## 💡 Tips

1. **Gunakan Find & Replace di VS Code:**
   - Find: `package exceptions;`
   - Replace: `package com.eventku.exceptions;`
   - Replace All

2. **Check Import:**
   - Cari semua `import models.` → replace dengan `import com.eventku.models.`
   - Cari semua `import services.` → replace dengan `import com.eventku.services.`

3. **Test setelah pindah:**
   ```bash
   mvn clean package -DskipTests
   ```

---

**Setelah reorganisasi, rebuild project dan jalankan!**

