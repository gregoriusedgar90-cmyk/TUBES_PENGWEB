// Authentication Logic

// Toggle password visibility
function togglePassword() {
    const passwordInput = document.getElementById('password');
    const icon = document.querySelector('.toggle-password i');
    
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        icon.className = 'fas fa-eye-slash';
    } else {
        passwordInput.type = 'password';
        icon.className = 'fas fa-eye';
    }
}

// Login Form Handler
if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        await handleLogin(username, password);
    });
}

// Register Form Handler
if (document.getElementById('registerForm')) {
    document.getElementById('registerForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const formData = {
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
            email: document.getElementById('email').value,
            role: document.getElementById('role').value,
            nomorTelepon: document.getElementById('nomorTelepon').value
        };

        // Add role-specific fields
        if (formData.role === 'Pembeli') {
            formData.alamat = document.getElementById('alamat').value;
        } else if (formData.role === 'Penjual') {
            formData.namaOrganisasi = document.getElementById('namaOrganisasi').value;
        }

        await handleRegister(formData);
    });
}

// Handle Login
async function handleLogin(username, password) {
    const btnText = document.getElementById('loginBtnText');
    const spinner = document.getElementById('loginSpinner');

    try {
        btnText.style.display = 'none';
        spinner.style.display = 'inline-block';

        const response = await fetch(getApiUrl(API_CONFIG.ENDPOINTS.LOGIN), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (data.success) {
            Storage.setUser(data.data);
            showAlert('Login berhasil! Mengalihkan...', 'success');
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);
        } else {
            showAlert(data.message, 'error');
        }

    } catch (error) {
        showAlert('Terjadi kesalahan: ' + error.message, 'error');
    } finally {
        btnText.style.display = 'inline';
        spinner.style.display = 'none';
    }
}

// Handle Register
async function handleRegister(formData) {
    const btnText = document.getElementById('registerBtnText');
    const spinner = document.getElementById('registerSpinner');

    try {
        btnText.style.display = 'none';
        spinner.style.display = 'inline-block';

        const response = await fetch(getApiUrl(API_CONFIG.ENDPOINTS.REGISTER), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        const data = await response.json();

        if (data.success) {
            showAlert('Registrasi berhasil! Mengalihkan ke login...', 'success');
            setTimeout(() => {
                window.location.href = 'login.html';
            }, 1500);
        } else {
            showAlert(data.message, 'error');
        }

    } catch (error) {
        showAlert('Terjadi kesalahan: ' + error.message, 'error');
    } finally {
        btnText.style.display = 'inline';
        spinner.style.display = 'none';
    }
}

// Demo Login
async function loginDemo(username, password) {
    document.getElementById('username').value = username;
    document.getElementById('password').value = password;
    await handleLogin(username, password);
}

// Logout
async function logout() {
    try {
        await fetch(getApiUrl(API_CONFIG.ENDPOINTS.LOGOUT), {
            method: 'POST'
        });
    } catch (error) {
        console.error('Logout error:', error);
    } finally {
        Storage.clear();
        window.location.href = 'login.html';
    }
}

// Check if user is logged in (for dashboard pages)
function checkAuth() {
    const user = Storage.getUser();
    if (!user) {
        window.location.href = 'login.html';
        return null;
    }
    return user;
}

