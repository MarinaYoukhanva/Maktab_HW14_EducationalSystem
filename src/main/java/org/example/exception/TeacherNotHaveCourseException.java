package org.example.exception;

public class TeacherNotHaveCourseException extends RuntimeException {
    public TeacherNotHaveCourseException() {
        super("the course is not presented by this teacher! ");
    }
}
