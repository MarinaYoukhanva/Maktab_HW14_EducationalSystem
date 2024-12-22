package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Teacher;
import org.example.repository.TeacherRepository;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Long, Teacher>
        implements TeacherRepository {
    public TeacherRepositoryImpl(Class<Teacher> entityClass) {
        super(entityClass);
    }
}
