# 🚀 Panduan Deploy EventKu ke Internet

## Pilihan Platform Deployment

Saya rekomendasikan 2 cara termudah:

### 🌟 Opsi 1: Railway.app (RECOMMENDED!)
- ✅ **Gratis** untuk project kecil
- ✅ **Paling mudah** untuk Spring Boot
- ✅ Auto-deploy dari GitHub
- ✅ Free tier: 500 jam/bulan

### 🌟 Opsi 2: Render.com
- ✅ **Gratis** untuk static sites & web services
- ✅ Auto-deploy dari GitHub
- ✅ Good free tier

---

## 🎯 Opsi 1: Deploy ke Railway.app (TERCEPAT!)

### Step 1: Persiapan Project

#### 1.1. Update application.properties

Buat file `src/main/resources/application-production.properties`:

```properties
# Production Configuration
server.port=${PORT:8080}
spring.profiles.active=production

# CORS - Update dengan domain frontend Anda nanti
cors.allowed-origins=${FRONTEND_URL:http://localhost:5500}

# Logging
logging.level.root=INFO
logging.level.com.eventku=INFO

# Jackson
spring.jackson.serialization.indent-output=false
spring.jackson.date-format=dd-MM-yyyy HH:mm:ss
```

#### 1.2. Buat Procfile (untuk Railway)

Buat file `Procfile` di root project:

```
web: java -Dserver.port=$PORT -Dspring.profiles.active=production -jar target/event-ticket-booking.jar
```

#### 1.3. Update pom.xml (pastikan sudah benar)

Sudah ada di `pom.xml` - tidak perlu diubah.

### Step 2: Push ke GitHub

```bash
# Initialize git (jika belum)
git init

# Add gitignore
echo "target/" >> .gitignore
echo "*.class" >> .gitignore
echo ".idea/" >> .gitignore

# Add all files
git add .

# Commit
git commit -m "Initial commit - EventKu web application"

# Create repository di GitHub, lalu:
git remote add origin https://github.com/username/eventku.git
git branch -M main
git push -u origin main
```

### Step 3: Deploy ke Railway

1. **Buka** https://railway.app/
2. **Sign up** dengan GitHub account
3. **New Project** → **Deploy from GitHub repo**
4. **Select repository** → pilih repository EventKu
5. Railway akan **auto-detect Spring Boot**
6. Klik **Deploy**

### Step 4: Configure Environment

Di Railway dashboard:
1. Klik project → **Variables**
2. Add variables:
   ```
   SPRING_PROFILES_ACTIVE=production
   PORT=8080
   ```

### Step 5: Get Backend URL

Setelah deploy sukses:
1. Klik **Settings** → **Generate Domain**
2. Copy URL (contoh: `eventku-production.railway.app`)
3. Backend URL: `https://eventku-production.railway.app`

---

## 📱 Deploy Frontend

### Opsi A: Netlify (Recommended untuk Frontend)

#### Step 1: Prepare Frontend

Update `frontend/js/config.js`:

```javascript
const API_CONFIG = {
    BASE_URL: 'https://eventku-production.railway.app/api',  // ← Backend URL dari Railway
    // ... rest of config
};
```

#### Step 2: Deploy ke Netlify

1. **Buka** https://www.netlify.com/
2. **Sign up** dengan GitHub
3. **New site from Git**
4. **Select repository** → EventKu
5. **Base directory:** `frontend`
6. Klik **Deploy site**

#### Step 3: Get Frontend URL

Netlify akan generate URL (contoh: `eventku.netlify.app`)

#### Step 4: Update CORS

Update `application-production.properties`:

```properties
cors.allowed-origins=https://eventku.netlify.app
```

Push ke GitHub, Railway akan auto-redeploy.

---

## 🎯 Opsi 2: Deploy ke Render.com

### Backend ke Render

1. **Buka** https://render.com/
2. **Sign up** dengan GitHub
3. **New** → **Web Service**
4. **Connect repository** → EventKu
5. **Configure:**
   ```
   Name: eventku-backend
   Build Command: mvn clean package -DskipTests
   Start Command: java -Dserver.port=$PORT -jar target/event-ticket-booking.jar
   ```
6. **Create Web Service**

### Frontend ke Render

1. **New** → **Static Site**
2. **Connect same repository**
3. **Configure:**
   ```
   Name: eventku-frontend
   Build Command: # leave empty
   Publish Directory: frontend
   ```
4. **Create Static Site**

---

## 🐳 Opsi 3: Deploy dengan Docker (VPS/Cloud)

Jika punya VPS (DigitalOcean, AWS, etc):

```bash
# Build and deploy
docker-compose -f docker-compose.prod.yml up -d
```

Buat `docker-compose.prod.yml`:

```yaml
version: '3.8'

services:
  backend:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=production
    restart: always

  frontend:
    image: nginx:alpine
    ports:
      - "80:80"
    volumes:
      - ./frontend:/usr/share/nginx/html:ro
      - ./nginx.conf:/etc/nginx/conf.d/default.conf:ro
    depends_on:
      - backend
    restart: always
```

---

## ⚙️ Update Frontend Config untuk Production

Setelah deploy, update `frontend/js/config.js`:

```javascript
// Detect environment
const isProduction = window.location.hostname !== 'localhost' && 
                     window.location.hostname !== '127.0.0.1';

const API_CONFIG = {
    BASE_URL: isProduction 
        ? 'https://eventku-production.railway.app/api'  // Production URL
        : 'http://localhost:8080/api',                   // Development URL
    
    ENDPOINTS: {
        // ... same as before
    }
};
```

---

## 🔍 Verifikasi Deployment

### Test Backend:

```bash
# Health check
curl https://eventku-production.railway.app/api/events

# Login test
curl -X POST https://eventku-production.railway.app/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### Test Frontend:

1. Buka URL frontend (contoh: `https://eventku.netlify.app`)
2. Test login dengan demo account
3. Test fitur browse event
4. Test pesan tiket
5. Test QR code generation

---

## 🎯 Checklist Deployment

### Pre-Deployment:
- [ ] Project di-compile tanpa error
- [ ] application-production.properties sudah dibuat
- [ ] Procfile sudah dibuat
- [ ] Project di-push ke GitHub
- [ ] .gitignore sudah proper

### Backend Deployment:
- [ ] Railway/Render account created
- [ ] Repository connected
- [ ] Environment variables set
- [ ] Backend deployed successfully
- [ ] Backend URL noted
- [ ] Test API endpoints

### Frontend Deployment:
- [ ] config.js updated dengan backend URL
- [ ] Frontend deployed to Netlify/Render
- [ ] Frontend URL noted
- [ ] CORS updated di backend
- [ ] Test website functionality

### Post-Deployment:
- [ ] Login works
- [ ] Register works
- [ ] Browse events works
- [ ] Create event works (penjual)
- [ ] Book ticket works (pembeli)
- [ ] QR code generation works
- [ ] All roles tested

---

## 🐛 Troubleshooting

### Backend tidak start:

**Check logs di Railway:**
```
Dashboard → Project → Deployments → View Logs
```

**Common issues:**
- Port not set: Add `PORT` environment variable
- Build failed: Check `mvn clean package` locally
- Startup error: Check application logs

### CORS Error:

Update `application-production.properties`:
```properties
cors.allowed-origins=https://your-frontend-url.netlify.app,https://www.your-domain.com
```

### Frontend tidak connect ke backend:

1. Check `config.js` - BASE_URL correct?
2. Open browser console - check error messages
3. Test backend URL directly in browser
4. Check CORS configuration

### 504 Gateway Timeout:

Railway free tier might sleep after inactivity:
- First request after sleep takes longer
- Wait 30-60 seconds for cold start
- Subsequent requests will be fast

---

## 💰 Pricing (Free Tiers)

### Railway.app
- ✅ **Free:** 500 execution hours/month
- ✅ **Free:** 1GB RAM
- ✅ **Free:** Auto SSL
- ⚠️ Sleeps after inactivity

### Render.com
- ✅ **Free:** Unlimited static sites
- ✅ **Free:** 750 hours/month web services
- ✅ **Free:** Auto SSL
- ⚠️ Spins down after 15 mins inactivity

### Netlify
- ✅ **Free:** Unlimited static sites
- ✅ **Free:** 100GB bandwidth/month
- ✅ **Free:** Auto SSL
- ✅ **Free:** Custom domain

---

## 🌐 Custom Domain (Optional)

### Jika punya domain sendiri:

#### Railway:
1. Dashboard → Settings → Domains
2. Add custom domain
3. Update DNS records

#### Netlify:
1. Site settings → Domain management
2. Add custom domain
3. Netlify auto-configures SSL

---

## 📊 Monitoring

### Railway Dashboard:
- View metrics
- Check logs
- Monitor usage
- Restart if needed

### Netlify Analytics:
- Page views
- Bandwidth usage
- Deploy status
- Form submissions (if any)

---

## 🎉 Success URLs

Setelah deployment berhasil, Anda akan punya:

**Backend API:**
```
https://eventku-production.railway.app/api
```

**Frontend Website:**
```
https://eventku.netlify.app
```

**Test dengan:**
1. Buka frontend URL
2. Login dengan demo account
3. Test semua fitur
4. Share link dengan teman/dosen!

---

## 📝 Update Dokumentasi

Update `README.md` dengan URLs production:

```markdown
## 🌐 Live Demo

- **Website:** https://eventku.netlify.app
- **Backend API:** https://eventku-production.railway.app/api
- **Documentation:** https://github.com/username/eventku

**Demo Accounts:**
- Admin: `admin` / `admin123`
- Penjual: `organizer1` / `organizer123`
- Pembeli: `pembeli1` / `pembeli123`
```

---

## 🔄 Continuous Deployment

Setelah setup, setiap push ke GitHub akan:
1. ✅ Auto-deploy backend di Railway
2. ✅ Auto-deploy frontend di Netlify
3. ✅ Live website updated otomatis!

```bash
# Make changes
git add .
git commit -m "Update feature"
git push

# Wait 2-3 minutes, website updated!
```

---

## 🎓 Tips untuk Presentasi

1. **Prepare URL list** untuk dosen/audience
2. **Test 1 jam sebelum presentasi** - pastikan live
3. **Siapkan backup** - jika server down, show localhost
4. **Screenshot** - backup jika demo gagal
5. **Demo accounts ready** - easy access untuk testing

---

## 📞 Need Help?

**Railway Community:**
- Discord: https://discord.gg/railway
- Docs: https://docs.railway.app/

**Render Community:**
- Community: https://community.render.com/
- Docs: https://render.com/docs

**Netlify Support:**
- Forums: https://answers.netlify.com/
- Docs: https://docs.netlify.com/

---

**🚀 Selamat! EventKu Anda akan segera live di internet!**

**Next:** Follow Step 1 → Step 5 untuk Railway deployment.

---

**Tips:** Start dengan Railway untuk backend (termudah!), lalu Netlify untuk frontend.

**Estimated Time:** 30-45 menit untuk full deployment.

Good luck! 🎉

