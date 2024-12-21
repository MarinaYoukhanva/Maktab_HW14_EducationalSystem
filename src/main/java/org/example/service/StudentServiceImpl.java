package org.example.service;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Student;
import org.example.repository.StudentRepository;

public class StudentServiceImpl extends BaseServiceImpl<Long, Student, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Student entity) {

    }
}
