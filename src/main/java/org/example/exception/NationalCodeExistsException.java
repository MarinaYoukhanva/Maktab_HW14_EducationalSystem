package org.example.exception;

public class NationalCodeExistsException extends RuntimeException {
    public NationalCodeExistsException() {
        super("National Code is already signed up! ");
    }
}
