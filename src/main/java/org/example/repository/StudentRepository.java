package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.Optional;

public interface StudentRepository extends BaseRepository<Long, Student> {

    Long emailExistence(Session session, Student student);
    Long usernameExistence(Session session, Student student);
    Long nationalCodeExistence(Session session, Student student);
    Long studentNumberExistence(Session session, String studentNumber);
    Optional<Student> findByStudentNumber(String studentNumber);

}
