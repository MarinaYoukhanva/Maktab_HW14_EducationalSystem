package org.example.repository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Student;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Long, Student>
        implements StudentRepository {

    public StudentRepositoryImpl(Class<Student> studentClass) {
        super(studentClass);
    }
}

