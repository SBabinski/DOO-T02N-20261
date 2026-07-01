package com.pedrohhm.exception;

public class TvMazeException extends RuntimeException {

    public TvMazeException(String message) {
        super(message);
    }

    public TvMazeException(String message, Throwable cause) {
        super(message, cause);
    }
}