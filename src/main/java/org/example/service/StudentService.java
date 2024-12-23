package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Student;
import org.hibernate.Session;

public interface StudentService extends BaseService<Long, Student> {

    Student login (String studentNumber, String password);
}
