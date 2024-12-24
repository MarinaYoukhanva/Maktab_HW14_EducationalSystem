package org.example.service;

import org.example.entity.Course;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;
import org.example.entity.dto.StudentDto;
import org.hibernate.Session;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourseDto> studentCourses(Long studentId);
    List<StudentDto> courseStudents(Long courseId);
    public void setScore(Long studentId, Long courseId, Double score);
}
