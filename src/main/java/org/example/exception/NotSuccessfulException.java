package org.example.exception;

public class NotSuccessfulException extends RuntimeException {
    public NotSuccessfulException() {
        super("Operation was not successful! ");
    }
}
