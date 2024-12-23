package org.example.exception;

public class PersonnelCodeExistsException extends RuntimeException {
    public PersonnelCodeExistsException() {
        super("personnel with this code already exists! ");
    }
}
