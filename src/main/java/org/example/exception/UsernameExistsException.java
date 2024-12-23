package org.example.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
        super("Username is already in use! ");
    }
}
