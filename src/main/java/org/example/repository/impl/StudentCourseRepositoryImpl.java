package org.example.repository.impl;

import org.example.SessionFactoryInstance;
import org.example.entity.StudentCourse;
import org.example.entity.dto.StudentCourseDto;
import org.example.entity.dto.StudentDto;
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
    @Override
    public List<StudentDto> courseStudents(Long courseId) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            String hql = """
                    SELECT new org.example.entity.dto.StudentDto(s.id, s.firstName, s.lastName,
                        s.email, s.studentNumber)
                    FROM StudentCourse sc
                    join sc.course c
                    join sc.student s
                    WHERE c.id = :courseId
                    """;
            return session.createQuery(hql, StudentDto.class)
                    .setParameter("courseId", courseId)
                    .list();
        }
    }
    @Override
    public int setScore(Session session, Long studentId, Long courseId, Double score){
        String hql = """
                    UPDATE StudentCourse sc
                    SET sc.score = :score
                    WHERE sc.id.courseId = :courseId
                    and sc.id.studentId = :studentId
                    """;
        return session.createMutationQuery(hql)
                .setParameter("score", score)
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .executeUpdate();
    }
}
