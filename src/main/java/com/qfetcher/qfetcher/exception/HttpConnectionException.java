package com.qfetcher.qfetcher.exception;

public class HttpConnectionException extends RuntimeException {
    public HttpConnectionException(String message) {
        super(message);
    }

    public HttpConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

