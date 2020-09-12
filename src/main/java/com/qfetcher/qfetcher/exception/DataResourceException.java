package com.qfetcher.qfetcher.exception;

public class DataResourceException extends RuntimeException {
    public DataResourceException(String message) {
        super(message);
    }

    public DataResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
