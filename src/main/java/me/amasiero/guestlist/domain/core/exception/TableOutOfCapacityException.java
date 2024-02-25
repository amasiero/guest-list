package me.amasiero.guestlist.domain.core.exception;

public class TableOutOfCapacityException extends RuntimeException {
    public TableOutOfCapacityException(String message) {
        super(message);
    }

    public TableOutOfCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
