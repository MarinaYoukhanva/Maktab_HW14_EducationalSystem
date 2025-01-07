package org.example.service.impl;

import org.example.base.config.SessionFactoryInstance;
import org.example.base.config.ApplicationContext;
import org.example.base.service.BaseServiceImpl;
import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.exception.NotFoundException;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;
import org.hibernate.Session;

import java.util.List;

public class CourseServiceImpl extends BaseServiceImpl<Long, Course, CourseRepository>
        implements CourseService {
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Course course, Course foundCourse) {
        foundCourse.setName(course.getName());
        foundCourse.setUnit(course.getUnit());
        foundCourse.setCapacity(course.getCapacity());
        foundCourse.setStartTime(course.getStartTime());
    }

    @Override
    public void infoLogicCheck(Session session, Course entity) {

    }

    @Override
    public Course setTeacher(Long courseId, Long teacherId) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Course course = getRepository().findById(session, courseId)
                                .orElseThrow(() -> new NotFoundException(Course.class));
                Teacher teacher = ApplicationContext.getTeacherRepository().findById(session, teacherId)
                        .orElseThrow(() -> new NotFoundException(Teacher.class));
                course.setTeacher(teacher);
                getRepository().save(session, course);
                session.getTransaction().commit();
                return course;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public List<Course> availableCourses(){
        return getRepository().availableCoursesForStudent();
    }

    @Override
    public List<Course> teacherCourses(Long teacherId){
        return getRepository().teacherCourses(teacherId);
    }
}
