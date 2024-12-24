package org.example.exception;

public class StudentNotHaveCourseException extends RuntimeException {
    public StudentNotHaveCourseException() {
        super("Student doesn't have this course! ");
    }
}
