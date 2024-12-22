package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Student;
import org.example.repository.StudentRepository;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Long, Student>
        implements StudentRepository {

    public StudentRepositoryImpl(Class<Student> studentClass) {
        super(studentClass);
    }
}

