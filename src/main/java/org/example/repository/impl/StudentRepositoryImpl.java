package org.example.repository.impl;

import org.example.base.config.SessionFactoryInstance;
import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.hibernate.Session;

import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Long, Student>
        implements StudentRepository {

    public StudentRepositoryImpl(Class<Student> studentClass) {
        super(studentClass);
    }

    @Override
    public Long emailExistence(Session session, Student student) {
        return session
                .createQuery("SELECT COUNT(id) FROM Student WHERE email = :email",
                        Long.class)
                .setParameter("email", student.getEmail())
                .getSingleResult();
    }

    @Override
    public Long usernameExistence(Session session, Student student) {
        return session
                .createQuery("SELECT COUNT(id) FROM Student WHERE username = :username",
                        Long.class)
                .setParameter("username", student.getUsername())
                .getSingleResult();
    }

    @Override
    public Long nationalCodeExistence(Session session, Student student) {
        return session
                .createQuery("SELECT COUNT(id) FROM Student WHERE nationalCode = :nationalCode",
                        Long.class)
                .setParameter("nationalCode", student.getNationalCode())
                .getSingleResult();
    }

    @Override
    public Long studentNumberExistence(Session session, String studentNumber) {
        return session
                .createQuery("SELECT COUNT(id) FROM Student WHERE studentNumber = :studentNumber",
                        Long.class)
                .setParameter("studentNumber", studentNumber)
                .getSingleResult();
    }

    @Override
    public Optional<Student> findByStudentNumber(String studentNumber) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            return session.createQuery("FROM Student WHERE studentNumber =: studentNumber",
                            Student.class)
                    .setParameter("studentNumber", studentNumber)
                    .uniqueResultOptional();
        }
    }

}

