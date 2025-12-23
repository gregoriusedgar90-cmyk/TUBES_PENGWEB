package exceptions;

/**
 * EXCEPTION HANDLING:
 * Custom exception untuk kasus event tidak ditemukan
 */
public class EventTidakDitemukanException extends Exception {
    public EventTidakDitemukanException(String message) {
        super(message);
    }
}

