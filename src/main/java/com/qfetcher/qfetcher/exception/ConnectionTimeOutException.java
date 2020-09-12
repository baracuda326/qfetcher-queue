package com.qfetcher.qfetcher.exception;

public class ConnectionTimeOutException extends RuntimeException {
    public ConnectionTimeOutException(String message) {
        super(message);
    }

    public ConnectionTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
