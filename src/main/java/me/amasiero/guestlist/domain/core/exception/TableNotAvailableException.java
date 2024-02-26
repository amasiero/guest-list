package me.amasiero.guestlist.domain.core.exception;

public class TableNotAvailableException extends RuntimeException {
    public TableNotAvailableException(String message) {
        super(message);
    }

    public TableNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
