package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Student;
import org.example.entity.dto.StudentDto;
import org.hibernate.Session;

import java.util.List;

public interface StudentService extends BaseService<Long, Student> {

    Student login (String studentNumber, String password);
    List<StudentDto> findAllDto();
    void takeCourse(Long studentId, Long courseId);
}
