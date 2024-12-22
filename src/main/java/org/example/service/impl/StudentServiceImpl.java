package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;

public class StudentServiceImpl extends BaseServiceImpl<Long, Student, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Student student, Student foundStudent) {
        foundStudent.setFirstName(student.getFirstName());
        foundStudent.setLastName(student.getLastName());
        foundStudent.setUsername(student.getUsername());
        foundStudent.setPassword(student.getPassword());
        foundStudent.setPhoneNumber(student.getPhoneNumber());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setStudentNumber(student.getStudentNumber());
    }
}
