// Tiket Display Logic

// Get tiket ID from URL parameter
const urlParams = new URLSearchParams(window.location.search);
const tiketId = urlParams.get('id');

if (!tiketId) {
    alert('Tiket ID tidak ditemukan!');
    window.location.href = 'dashboard.html';
}

// Load tiket data
async function loadTiketData() {
    try {
        const user = Storage.getUser();
        if (!user) {
            window.location.href = 'login.html';
            return;
        }

        // Get all tikets for user
        const response = await fetch(getApiUrl(`/transaksi/tiket/pembeli/${user.userId}`));
        const result = await response.json();

        if (result.success) {
            const tiket = result.data.find(t => t.tiketId === tiketId);
            
            if (!tiket) {
                alert('Tiket tidak ditemukan!');
                window.location.href = 'dashboard.html';
                return;
            }

            // Display tiket data
            displayTiketData(tiket);

            // Load QR Code
            loadQRCode(tiket.tiketId);
        } else {
            alert('Gagal memuat data tiket');
        }

    } catch (error) {
        console.error('Error loading tiket:', error);
        alert('Terjadi kesalahan saat memuat tiket');
    }
}

// Display tiket information
function displayTiketData(tiket) {
    document.getElementById('eventName').textContent = tiket.namaEvent;
    document.getElementById('pembeliName').textContent = tiket.namaPembeli;
    document.getElementById('eventDate').textContent = formatDate(tiket.tanggalEvent);
    document.getElementById('eventLocation').textContent = tiket.lokasi;
    document.getElementById('tiketId').textContent = tiket.tiketId;
    document.getElementById('barcodeNumber').textContent = tiket.barcodeNumber;

    // Update status badge
    const statusBadge = document.getElementById('statusBadge');
    if (tiket.used) {
        statusBadge.textContent = 'SUDAH DIGUNAKAN';
        statusBadge.classList.add('used');
    }
}

// Load QR Code image from backend
async function loadQRCode(tiketId) {
    try {
        const qrLoading = document.getElementById('qrLoading');
        const qrImage = document.getElementById('qrCodeImage');

        // Show loading
        qrLoading.style.display = 'flex';
        qrImage.style.display = 'none';

        // Get QR code image from backend
        const qrCodeUrl = getApiUrl(`/qrcode/tiket/${tiketId}`);
        
        // Load image
        qrImage.onload = function() {
            qrLoading.style.display = 'none';
            qrImage.style.display = 'block';
        };

        qrImage.onerror = function() {
            qrLoading.innerHTML = '<p style="color: red;">Gagal memuat QR Code</p>';
        };

        qrImage.src = qrCodeUrl;

    } catch (error) {
        console.error('Error loading QR code:', error);
        document.getElementById('qrLoading').innerHTML = '<p style="color: red;">Error loading QR code</p>';
    }
}

// Download tiket as image
function downloadTiket() {
    // Convert tiket card to canvas and download
    alert('Fitur download akan tersedia segera!');
    
    // TODO: Implement with html2canvas library
    // html2canvas(document.querySelector('.tiket-card')).then(canvas => {
    //     const link = document.createElement('a');
    //     link.download = `tiket-${tiketId}.png`;
    //     link.href = canvas.toDataURL();
    //     link.click();
    // });
}

// Share tiket
function shareTiket() {
    if (navigator.share) {
        navigator.share({
            title: 'Tiket EventKu',
            text: 'Saya akan menghadiri event!',
            url: window.location.href
        }).catch(err => console.log('Error sharing:', err));
    } else {
        // Fallback: Copy link
        const link = window.location.href;
        navigator.clipboard.writeText(link).then(() => {
            alert('Link tiket berhasil disalin!');
        });
    }
}

// Print tiket
function printTiket() {
    window.print();
}

// Load data when page loads
document.addEventListener('DOMContentLoaded', loadTiketData);

