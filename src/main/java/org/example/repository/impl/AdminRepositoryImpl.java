package org.example.repository.impl;

import org.example.base.config.SessionFactoryInstance;
import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Admin;
import org.example.repository.AdminRepository;
import org.hibernate.Session;

import java.util.Optional;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Long, Admin>
        implements AdminRepository {

    public AdminRepositoryImpl(Class<Admin> entityClass) {
        super(entityClass);
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            return session.createQuery("FROM Admin WHERE username =: username",
                            Admin.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        }    }
}
