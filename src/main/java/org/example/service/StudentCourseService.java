package org.example.service;

import org.example.entity.Course;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;

import java.util.List;

public interface StudentCourseService {
    List<StudentCourseDto> studentCourses(Long studentId);
}
