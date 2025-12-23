# 📱 QR Code Feature - EventKu

## Fitur QR Code yang Bisa Di-Scan

Sekarang EventKu memiliki fitur **generate QR Code yang sebenarnya** menggunakan library ZXing (Zebra Crossing)!

---

## ✨ Fitur Baru

### 1. Generate QR Code Real
- QR Code berupa **image PNG** (bukan hanya text)
- Bisa di-scan dengan **aplikasi scanner manapun**
- Resolusi tinggi (400x400 pixels)
- Error correction level: HIGH

### 2. Halaman Tiket Virtual
- **URL**: `frontend/tiket.html?id={tiketId}`
- Design modern dan cantik
- Menampilkan:
  - Informasi event lengkap
  - QR Code yang bisa di-scan
  - Barcode number
  - Status tiket (Aktif/Sudah Digunakan)

### 3. Verify QR Code
- Scan QR code untuk verifikasi
- Cek apakah tiket valid
- Cek apakah tiket sudah digunakan
- Mark tiket as used saat masuk event

### 4. Download & Share
- Download tiket sebagai image
- Share tiket via WhatsApp/social media
- Print tiket

---

## 🔧 Yang Sudah Ditambahkan

### Backend (Java):

1. **Dependency Baru di `pom.xml`:**
```xml
<!-- ZXing untuk QR Code Generation -->
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.5.2</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.5.2</version>
</dependency>
```

2. **Class Baru:**
   - `src/main/java/com/eventku/util/QRCodeGenerator.java` - Utility untuk generate QR code
   - `src/main/java/com/eventku/controller/QRCodeController.java` - REST API untuk QR code

### Frontend (HTML/CSS/JS):

1. **File Baru:**
   - `frontend/tiket.html` - Halaman display tiket virtual
   - `frontend/css/tiket.css` - Styling untuk tiket
   - `frontend/js/tiket.js` - Logic untuk load tiket & QR code

---

## 🚀 Cara Menggunakan

### Step 1: Rebuild Backend

Karena ada dependency baru, rebuild backend:

```bash
# Clean dan rebuild
mvn clean package -DskipTests

# Run backend
mvn spring-boot:run
```

### Step 2: Test Flow Pembelian Tiket

1. Login sebagai **Pembeli** (`pembeli1` / `pembeli123`)
2. Browse event dan pilih event
3. Pesan tiket
4. Proses pembayaran
5. Setelah pembayaran berhasil → **Lihat Tiket**
6. Klik tiket untuk melihat detail
7. **QR Code akan otomatis di-generate!**

### Step 3: Akses Tiket Langsung

Jika sudah tahu tiket ID, bisa akses langsung:

```
http://localhost:5500/tiket.html?id={tiketId}
```

Contoh:
```
http://localhost:5500/tiket.html?id=TKT1734444444001
```

---

## 📡 API Endpoints Baru

### 1. Generate QR Code Image
```
GET /api/qrcode/tiket/{tiketId}
```

**Response:** PNG image (byte array)

**Headers:**
```
Content-Type: image/png
Cache-Control: max-age=3600
```

**Contoh:**
```html
<img src="http://localhost:8080/api/qrcode/tiket/TKT123" alt="QR Code">
```

### 2. Verify QR Code
```
GET /api/qrcode/verify/{qrData}
```

**Response:**
```json
{
  "message": "QR Code Valid - Tiket untuk: Konser Musik Rock 2025"
}
```

**Status Codes:**
- `200 OK` - Valid
- `404 NOT FOUND` - Tiket tidak ditemukan
- `409 CONFLICT` - Tiket sudah digunakan
- `400 BAD REQUEST` - QR Code tidak valid

### 3. Use Tiket (Mark as Used)
```
POST /api/qrcode/use/{tiketId}
```

**Response:**
```json
{
  "message": "Tiket berhasil digunakan! Selamat menikmati: Konser Musik Rock 2025"
}
```

---

## 🔍 Format QR Code Data

QR Code berisi string dengan format:

```
TIKET-{tiketId}|EVENT-{eventId}|PEMBELI-{pembeliId}|DATE-{timestamp}|VERIFY-EVENTKU
```

**Contoh:**
```
TIKET-TKT1734444444001|EVENT-EVT001|PEMBELI-PMB001|DATE-1734444444000|VERIFY-EVENTKU
```

**Kegunaan:**
- `TIKET-*` - Unique tiket identifier
- `EVENT-*` - Event yang dipesan
- `PEMBELI-*` - Pemesan tiket
- `DATE-*` - Timestamp pembuatan
- `VERIFY-EVENTKU` - Security signature

---

## 🎨 Tampilan Tiket Virtual

```
╔═══════════════════════════════════════╗
║            EventKu                    ║
║                              [AKTIF]  ║
╠═══════════════════════════════════════╣
║                                       ║
║  Konser Musik Rock 2025               ║
║                                       ║
║  👤 Nama: John Doe                    ║
║  📅 Tanggal: 31 Des 2025, 19:00       ║
║  📍 Lokasi: Stadion Utama Jakarta     ║
║  🎫 Tiket ID: TKT1734444444001        ║
║                                       ║
║  ┌─────────────────────────────┐     ║
║  │                             │     ║
║  │      [QR CODE IMAGE]        │     ║
║  │                             │     ║
║  └─────────────────────────────┘     ║
║                                       ║
║  Tunjukkan QR code saat masuk event  ║
║                                       ║
║  |||||| |||| ||||| |||| ||||||       ║
║  1234567890                           ║
║                                       ║
║  [Download] [Share] [Print]          ║
║                                       ║
╚═══════════════════════════════════════╝
```

---

## 📱 Cara Scan QR Code

### Untuk Testing:

1. **Menggunakan HP:**
   - Buka camera app
   - Arahkan ke QR code di layar komputer
   - Tap link yang muncul

2. **Menggunakan QR Scanner App:**
   - Download app "QR Scanner" (iOS/Android)
   - Scan QR code
   - Akan muncul text dengan format: `TIKET-xxx|EVENT-xxx|...`

3. **Untuk Verifikasi:**
   - Copy QR data yang di-scan
   - Akses: `GET /api/qrcode/verify/{qrData}`
   - Atau buat scanner app tersendiri

---

## 🔐 Security Features

1. **Unique QR Data:**
   - Setiap tiket punya QR code unik
   - Timestamp untuk prevent reuse

2. **Validation:**
   - Cek format QR data
   - Cek apakah tiket exist
   - Cek apakah sudah digunakan

3. **One-Time Use:**
   - Tiket hanya bisa digunakan sekali
   - Status berubah menjadi "USED" setelah di-scan

---

## 🎯 Update Dashboard Pembeli

Setelah pembayaran berhasil, tambahkan tombol "Lihat Tiket" di dashboard pembeli:

```javascript
// Di dashboard-pembeli.js
function displayRiwayatTransaksi(transaksis) {
    transaksis.forEach(transaksi => {
        if (transaksi.statusPembayaran === 'Paid') {
            // Tambahkan button
            const btnLihatTiket = document.createElement('button');
            btnLihatTiket.textContent = 'Lihat Tiket';
            btnLihatTiket.onclick = () => {
                // Get tiket for this transaksi
                fetch(getApiUrl(`/transaksi/tiket/pembeli/${userId}`))
                    .then(res => res.json())
                    .then(data => {
                        const tiket = data.data.find(t => 
                            t.transaksiId === transaksi.transaksiId
                        );
                        if (tiket) {
                            window.location.href = `tiket.html?id=${tiket.tiketId}`;
                        }
                    });
            };
        }
    });
}
```

---

## 🐛 Troubleshooting

### QR Code tidak muncul:

1. **Check backend running:**
   ```bash
   curl http://localhost:8080/api/qrcode/tiket/TKT123
   ```
   Harus return binary image data

2. **Check dependency:**
   ```bash
   mvn dependency:tree | grep zxing
   ```
   Harus muncul ZXing libraries

3. **Rebuild:**
   ```bash
   mvn clean package -DskipTests
   mvn spring-boot:run
   ```

### CORS Error saat load image:

Sudah di-handle di `Application.java`, tapi jika masih error:
- Clear browser cache
- Restart backend
- Check console untuk error message

### Image tidak bisa di-scan:

- Pastikan image size minimal 300x300px
- Check lighting saat scan
- Gunakan QR scanner app yang bagus

---

## 🎉 Fitur Tambahan (Optional)

### 1. Scanner App untuk Admin/Penjual

Buat halaman khusus untuk scan QR code:

```html
<!-- scanner.html -->
<video id="qrVideo"></video>
<script src="https://cdn.jsdelivr.net/npm/jsqr@1.4.0/dist/jsQR.js"></script>
<script>
  // Implement camera QR scanner
  // Parse QR data
  // Call /api/qrcode/use/{tiketId}
</script>
```

### 2. Email Tiket

Kirim tiket via email setelah pembayaran:
- Generate PDF with QR code
- Send via email service (SendGrid/Mailgun)

### 3. WhatsApp Integration

Share tiket langsung ke WhatsApp:
```javascript
function shareToWhatsApp() {
    const url = window.location.href;
    window.open(`https://wa.me/?text=Tiket saya: ${url}`);
}
```

---

## ✅ Checklist

- [x] ZXing dependency added
- [x] QRCodeGenerator utility created
- [x] QRCodeController API created
- [x] Tiket.html page created
- [x] QR Code styling (CSS)
- [x] Load QR code from backend
- [x] Verify QR endpoint
- [x] Use tiket endpoint
- [x] Download/Share/Print buttons
- [ ] Update dashboard pembeli (show tiket button)
- [ ] Scanner app (optional)
- [ ] Email integration (optional)

---

## 📚 Resources

- **ZXing Documentation**: https://github.com/zxing/zxing
- **QR Code Best Practices**: https://www.qr-code-generator.com/qr-code-marketing/qr-codes-basics/
- **jsQR (Frontend Scanner)**: https://github.com/cozmo/jsQR

---

**🎉 Sekarang EventKu punya QR Code yang betulan bisa di-scan!**

**Selamat mencoba! 📱✨**

