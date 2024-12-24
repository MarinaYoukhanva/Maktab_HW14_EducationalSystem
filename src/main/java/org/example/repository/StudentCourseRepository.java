package org.example.repository;

import org.example.entity.Course;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;
import org.example.entity.dto.StudentDto;
import org.hibernate.Session;

import java.util.List;

public interface StudentCourseRepository {

    StudentCourse save(Session session, StudentCourse studentCourse);

    List<StudentCourseDto> studentCourses(Long studentId);

    List<StudentDto> courseStudents(Long courseId);
}
