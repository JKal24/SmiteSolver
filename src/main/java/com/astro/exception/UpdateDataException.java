package com.astro.exception;

public class UpdateDataException extends RuntimeException {
    public UpdateDataException() {
        super();
    }

    public UpdateDataException(String message) {
        super(message);
    }
}
