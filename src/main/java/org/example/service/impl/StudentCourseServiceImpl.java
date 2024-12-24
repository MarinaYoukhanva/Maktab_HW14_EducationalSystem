package org.example.service.impl;

import lombok.Getter;
import org.example.entity.Course;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;
import org.example.repository.StudentCourseRepository;
import org.example.service.StudentCourseService;

import java.util.List;

@Getter
public class StudentCourseServiceImpl implements StudentCourseService {
    private final StudentCourseRepository repository;

    public StudentCourseServiceImpl(StudentCourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentCourseDto> studentCourses(Long studentId) {
        return getRepository().studentCourses(studentId);
    }
}
