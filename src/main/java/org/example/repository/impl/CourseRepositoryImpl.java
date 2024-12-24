package org.example.repository.impl;

import org.example.SessionFactoryInstance;
import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.repository.CourseRepository;
import org.hibernate.Session;

import java.util.List;

public class CourseRepositoryImpl extends BaseRepositoryImpl<Long, Course>
        implements CourseRepository {
    public CourseRepositoryImpl(Class<Course> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Course> availableCourses() {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            String hql = """
                    FROM Course c
                    WHERE c.capacity > 0
                    AND c.startTime > current timestamp
                    """;
            return session.createQuery(hql, Course.class).list();
        }
    }
}
