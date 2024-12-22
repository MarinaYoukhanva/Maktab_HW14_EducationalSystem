package org.example.base.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.example.SessionFactoryInstance;
import org.example.base.model.BaseEntity;
import org.example.base.repository.BaseRepository;
import org.example.validation.Validation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class BaseServiceImpl<ID extends Serializable, T extends BaseEntity<ID>,
        R extends BaseRepository<ID, T>> implements BaseService<ID, T> {

    private final R repository;
    Validation<ID, T> validation = new Validation<>();

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public T save(T entity) {
        Set<ConstraintViolation<T>> violations=  validation.checkValidations(entity);
        if (!violations.isEmpty())
            throw new ValidationException(String.valueOf(violations));
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                repository.save(session, entity);
                session.getTransaction().commit();
                return entity;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public T update(T entity) {
        Set<ConstraintViolation<T>> violations=  validation.checkValidations(entity);
        if (!violations.isEmpty())
            throw new ValidationException(String.valueOf(violations));
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var foundEntity = repository.findById(session, entity.getId())
                        .orElseThrow(() -> new RuntimeException("Contact not found"));
                updateColumns(entity, foundEntity);
                repository.save(session, foundEntity);
                session.getTransaction().commit();
                return foundEntity;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<T> findAll() {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = repository.findAll(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var result = repository.findById(session, id);
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(ID id) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                var affectedRows = repository.delete(session, id);
                if (affectedRows == 0)
                    throw new RuntimeException("Contact Not Found");
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
    public abstract void updateColumns (T entity, T foundEntity);
}

