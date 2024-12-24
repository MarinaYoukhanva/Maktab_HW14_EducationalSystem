package org.example.repository.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.Course;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;
import org.example.repository.StudentCourseRepository;
import org.hibernate.Session;

import java.util.List;

public class StudentCourseRepositoryImpl implements StudentCourseRepository {

    @Override
    public StudentCourse save(Session session, StudentCourse studentCourse) {
        session.persist(studentCourse);
        return studentCourse;
    }

    @Override
    public List<StudentCourseDto> studentCourses(Long studentId) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            String hql = """
                    SELECT new org.example.entity.dto.StudentCourseDto(c.id, s.id, c.teacher.id,
                            c.name, c.unit, c.startTime, sc.score)
                    FROM StudentCourse sc
                    join sc.course c
                    join sc.student s
                    WHERE s.id = :studentId
                    """;
            return session.createQuery(hql, StudentCourseDto.class)
                    .setParameter("studentId", studentId)
                    .list();
        }
    }
}
