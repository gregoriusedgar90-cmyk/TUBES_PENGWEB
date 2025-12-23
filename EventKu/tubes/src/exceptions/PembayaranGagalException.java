package exceptions;

/**
 * EXCEPTION HANDLING:
 * Custom exception untuk kasus pembayaran gagal
 */
public class PembayaranGagalException extends Exception {
    public PembayaranGagalException(String message) {
        super(message);
    }
}

