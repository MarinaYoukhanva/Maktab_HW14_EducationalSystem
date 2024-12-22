package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Course;
import org.example.repository.CourseRepository;

public class CourseRepositoryImpl extends BaseRepositoryImpl<Long, Course>
        implements CourseRepository {
    public CourseRepositoryImpl(Class<Course> entityClass) {
        super(entityClass);
    }
}
