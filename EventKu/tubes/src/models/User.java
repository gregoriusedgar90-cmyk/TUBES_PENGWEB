package models;

/**
 * ABSTRACTION & INHERITANCE:
 * Abstract class User sebagai parent class untuk semua jenis user
 * Menerapkan prinsip abstraksi dengan method abstract displayMenu()
 */
public abstract class User {
    // ENCAPSULATION: Private attributes dengan getter/setter
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role; // "Admin", "Penjual", "Pembeli"
    private boolean isActive;

    // Constructor
    public User(String userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = true;
    }

    // ABSTRACTION: Method abstract yang harus diimplementasikan oleh child class
    public abstract void displayMenu();
    
    public abstract String getMenuOptions();

    // ENCAPSULATION: Getter methods
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    // ENCAPSULATION: Setter methods dengan validasi
    public void setUsername(String username) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

