package exceptions;

/**
 * EXCEPTION HANDLING:
 * Custom exception untuk kasus login gagal
 */
public class LoginGagalException extends Exception {
    public LoginGagalException(String message) {
        super(message);
    }
}

