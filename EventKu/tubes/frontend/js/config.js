// API Configuration
const API_CONFIG = {
    BASE_URL: 'http://localhost:8080/api',
    ENDPOINTS: {
        // Auth
        LOGIN: '/auth/login',
        REGISTER: '/auth/register',
        LOGOUT: '/auth/logout',
        CURRENT_USER: '/auth/current',

        // Events
        EVENTS: '/events',
        EVENTS_PENDING: '/events/pending',
        EVENTS_SEARCH: '/events/search',
        EVENT_BY_ID: (id) => `/events/${id}`,
        EVENTS_BY_PENJUAL: (id) => `/events/penjual/${id}`,
        EVENT_VERIFY: (id) => `/events/${id}/verify`,

        // Transaksi
        TRANSAKSI: '/transaksi',
        TRANSAKSI_BAYAR: (id) => `/transaksi/${id}/bayar`,
        TRANSAKSI_BY_PEMBELI: (id) => `/transaksi/pembeli/${id}`,
        TIKET_BY_PEMBELI: (id) => `/transaksi/tiket/pembeli/${id}`,

        // Admin
        ADMIN_USERS: '/admin/users',
        ADMIN_STATS: '/admin/stats',
        ADMIN_DELETE_USER: (id) => `/admin/users/${id}`
    }
};

// Helper function untuk generate full URL
function getApiUrl(endpoint, params = {}) {
    let url = API_CONFIG.BASE_URL + endpoint;
    
    // Add query parameters if any
    const queryString = new URLSearchParams(params).toString();
    if (queryString) {
        url += '?' + queryString;
    }
    
    return url;
}

// Storage keys
const STORAGE_KEYS = {
    USER: 'eventku_user',
    TOKEN: 'eventku_token'
};

// Helper functions untuk localStorage
const Storage = {
    setUser: (user) => {
        localStorage.setItem(STORAGE_KEYS.USER, JSON.stringify(user));
    },
    getUser: () => {
        const user = localStorage.getItem(STORAGE_KEYS.USER);
        return user ? JSON.parse(user) : null;
    },
    removeUser: () => {
        localStorage.removeItem(STORAGE_KEYS.USER);
    },
    setToken: (token) => {
        localStorage.setItem(STORAGE_KEYS.TOKEN, token);
    },
    getToken: () => {
        return localStorage.getItem(STORAGE_KEYS.TOKEN);
    },
    removeToken: () => {
        localStorage.removeItem(STORAGE_KEYS.TOKEN);
    },
    clear: () => {
        localStorage.removeItem(STORAGE_KEYS.USER);
        localStorage.removeItem(STORAGE_KEYS.TOKEN);
    }
};

// Date formatter
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('id-ID', {
        day: '2-digit',
        month: 'long',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Currency formatter
function formatCurrency(amount) {
    return new Intl.NumberFormat('id-ID', {
        style: 'currency',
        currency: 'IDR',
        minimumFractionDigits: 0
    }).format(amount);
}

// Alert helper
function showAlert(message, type = 'info') {
    const alert = document.getElementById('alert');
    if (!alert) return;

    alert.textContent = message;
    alert.className = `alert alert-${type}`;
    alert.style.display = 'block';

    setTimeout(() => {
        alert.style.display = 'none';
    }, 3000);
}

