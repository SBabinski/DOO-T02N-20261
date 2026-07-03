package com.pedrohhm.exception;

public class ApiConnectionException extends RuntimeException {

    public ApiConnectionException(String message) {
        super(message);
    }

    public ApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
