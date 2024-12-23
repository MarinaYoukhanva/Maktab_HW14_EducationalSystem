package org.example.repository.impl;

import org.example.SessionFactoryInstance;
import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.repository.TeacherRepository;
import org.hibernate.Session;

import java.util.Optional;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Long, Teacher>
        implements TeacherRepository {
    public TeacherRepositoryImpl(Class<Teacher> entityClass) {
        super(entityClass);
    }

    @Override
    public Long emailExistence(Session session, Teacher teacher) {
        return session
                .createQuery("SELECT COUNT(id) FROM Teacher WHERE email = :email",
                        Long.class)
                .setParameter("email", teacher.getEmail())
                .getSingleResult();
    }

    @Override
    public Long usernameExistence(Session session, Teacher teacher) {
        return session
                .createQuery("SELECT COUNT(id) FROM Teacher WHERE username = :username",
                        Long.class)
                .setParameter("username", teacher.getUsername())
                .getSingleResult();
    }

    @Override
    public Long nationalCodeExistence(Session session, Teacher teacher) {
        return session
                .createQuery("SELECT COUNT(id) FROM Teacher WHERE nationalCode = :nationalCode",
                        Long.class)
                .setParameter("nationalCode", teacher.getNationalCode())
                .getSingleResult();
    }

    @Override
    public Long personnelCodeExistence(Session session, String personnelCode) {
        return session
                .createQuery("SELECT COUNT(id) FROM Teacher WHERE personnelCode = :personnelCode",
                        Long.class)
                .setParameter("personnelCode", personnelCode)
                .getSingleResult();
    }

    @Override
    public Optional<Teacher> findByPersonnelCode(String personnelCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            return session.createQuery("FROM Teacher WHERE personnelCode =: personnelCode",
                            Teacher.class)
                    .setParameter("personnelCode", personnelCode)
                    .uniqueResultOptional();
        }    }
}
