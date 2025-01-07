package org.example.base.config;

import org.hibernate.Session;

import java.util.function.Function;

public class SessionManager {
    public <T> T executeWithinTransaction(Function<Session, T> action) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                T result = action.apply(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
