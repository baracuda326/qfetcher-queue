package com.qfetcher.qfetcher.exception;

public class NoValidFieldException extends RuntimeException {
    public NoValidFieldException(String message) {
        super(message);
    }

    public NoValidFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
