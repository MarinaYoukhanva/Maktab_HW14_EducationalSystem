package org.example.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException() {
        super("Email is already in use! ");
    }
}
