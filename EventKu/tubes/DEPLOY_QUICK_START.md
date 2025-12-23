# ⚡ Deploy EventKu - Quick Start

## 🚀 Cara Tercepat Deploy (15 Menit!)

### Step 1: Push ke GitHub (5 menit)

```bash
# Di folder project
cd "D:\Kuliah\PBO TP\tubes"

# Initialize git
git init

# Add all files
git add .

# Commit
git commit -m "EventKu - Ready for deployment"

# Buat repository di GitHub.com, lalu:
git remote add origin https://github.com/USERNAME/eventku.git
git branch -M main
git push -u origin main
```

### Step 2: Deploy Backend ke Railway (5 menit)

1. Buka: https://railway.app/
2. **Sign Up** dengan GitHub
3. **New Project** → **Deploy from GitHub repo**
4. Pilih repository **eventku**
5. Railway auto-detect Spring Boot → **Deploy**
6. Tunggu build selesai (~3 menit)
7. **Settings** → **Generate Domain**
8. Copy URL (contoh: `eventku-production.up.railway.app`)

✅ **Backend URL:** `https://eventku-production.up.railway.app`

### Step 3: Deploy Frontend ke Netlify (5 menit)

1. Update `frontend/js/config.js`:
```javascript
const API_CONFIG = {
    BASE_URL: 'https://eventku-production.up.railway.app/api',  // ← Paste Railway URL
    // ... (rest tetap sama)
};
```

2. Commit & push:
```bash
git add frontend/js/config.js
git commit -m "Update API URL for production"
git push
```

3. Buka: https://www.netlify.com/
4. **Sign Up** dengan GitHub
5. **Add new site** → **Import from Git**
6. Pilih repository **eventku**
7. **Base directory:** `frontend`
8. **Publish directory:** (kosongkan)
9. **Deploy**

✅ **Frontend URL:** `https://eventku-xyz.netlify.app`

### Step 4: Update CORS (2 menit)

1. Copy URL Netlify Anda
2. Buka `src/main/resources/application-production.properties`
3. Update:
```properties
cors.allowed-origins=https://eventku-xyz.netlify.app
```
4. Commit & push:
```bash
git add src/main/resources/application-production.properties
git commit -m "Update CORS for Netlify"
git push
```

Railway akan auto-redeploy (~2 menit).

### Step 5: Test! 🎉

1. Buka URL Netlify Anda
2. Klik **Login**
3. Login dengan: `admin` / `admin123`
4. Test fitur!

---

## 🎯 URLs Penting

Setelah selesai, Anda punya:

- **Website:** `https://eventku-xyz.netlify.app`
- **Backend API:** `https://eventku-production.up.railway.app/api`
- **GitHub Repo:** `https://github.com/USERNAME/eventku`

---

## 📱 Share dengan Teman/Dosen

```
🎫 EventKu - Sistem Pemesanan Tiket Event Online

Website: https://eventku-xyz.netlify.app

Demo Accounts:
• Admin: admin / admin123
• Penjual: organizer1 / organizer123  
• Pembeli: pembeli1 / pembeli123

GitHub: https://github.com/USERNAME/eventku
```

---

## 🐛 Troubleshooting Cepat

### Frontend tidak connect:
```bash
# Check config.js
cat frontend/js/config.js

# Pastikan BASE_URL correct
```

### Backend error:
```bash
# Check Railway logs
# Dashboard → Deployments → View Logs
```

### CORS Error:
```bash
# Update application-production.properties
# Tambahkan Netlify URL ke cors.allowed-origins
```

---

## 🔄 Update Deployment

Setelah setup, setiap kali update:

```bash
# Make changes to code
git add .
git commit -m "Update feature xyz"
git push

# Railway & Netlify auto-deploy! ✨
```

---

## 💡 Tips

1. **Save URLs** - Catat Railway & Netlify URLs
2. **Test before presentasi** - Check 1 jam sebelum
3. **Prepare screenshots** - Backup jika server down
4. **Demo accounts ready** - Easy untuk testing

---

## 📞 Help

Stuck? Check **DEPLOYMENT_STEPS.md** untuk detail lengkap.

---

**Total Time:** ~15 menit  
**Cost:** $0 (Free tier)  
**Difficulty:** ⭐⭐☆☆☆ (Easy!)

🚀 **Let's deploy!**

