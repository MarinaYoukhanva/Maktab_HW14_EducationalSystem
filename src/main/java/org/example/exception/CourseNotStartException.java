package org.example.exception;

public class CourseNotStartException extends RuntimeException {
    public CourseNotStartException() {
        super("The course is not started yet! ");
    }
}
