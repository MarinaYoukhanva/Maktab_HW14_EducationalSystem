package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;
import org.hibernate.Session;

public class CourseServiceImpl extends BaseServiceImpl<Long, Course, CourseRepository>
        implements CourseService {
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Course entity, Course foundEntity) {

    }

    @Override
    public void infoLogicCheck(Session session, Course entity) {

    }
}
