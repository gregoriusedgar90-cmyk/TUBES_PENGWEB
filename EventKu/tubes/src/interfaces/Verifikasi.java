package interfaces;

/**
 * INTERFACE:
 * Interface Verifikasi untuk proses verifikasi berbagai entitas
 * Menerapkan prinsip polymorphism
 */
public interface Verifikasi {
    boolean verifikasi(String id);
    void approveVerifikasi(String id);
    void rejectVerifikasi(String id);
    String getStatusVerifikasi(String id);
}

