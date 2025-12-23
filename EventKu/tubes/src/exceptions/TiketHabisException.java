package exceptions;

/**
 * EXCEPTION HANDLING:
 * Custom exception untuk kasus tiket habis
 */
public class TiketHabisException extends Exception {
    public TiketHabisException(String message) {
        super(message);
    }
    
    public TiketHabisException(String eventName, int requested, int available) {
        super(String.format("Tiket untuk event '%s' tidak mencukupi! Diminta: %d, Tersedia: %d", 
                           eventName, requested, available));
    }
}

