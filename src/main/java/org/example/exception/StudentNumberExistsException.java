package org.example.exception;

public class StudentNumberExistsException extends RuntimeException {
    public StudentNumberExistsException() {
        super("Student with this number already exists! ");
    }
}
