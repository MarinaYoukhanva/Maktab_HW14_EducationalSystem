package org.example.service.impl;

import lombok.Getter;
import org.example.SessionFactoryInstance;
import org.example.entity.dto.StudentCourseDto;
import org.example.entity.dto.StudentDto;
import org.example.exception.NotSuccessfulException;
import org.example.exception.StudentNotHaveCourseException;
import org.example.repository.StudentCourseRepository;
import org.example.service.StudentCourseService;
import org.hibernate.Session;

import java.util.List;

@Getter
public class StudentCourseServiceImpl implements StudentCourseService {
    private final StudentCourseRepository repository;

    public StudentCourseServiceImpl(StudentCourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentCourseDto> studentCourses(Long studentId) {
        return getRepository().studentCourses(studentId);
    }

    @Override
    public List<StudentDto> courseStudents(Long courseId) {
        return getRepository().courseStudents(courseId);
    }

    @Override
    public void setScore(Long studentId, Long courseId, Double score) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()){
            try {
                session.beginTransaction();
                if (!courseStudents(courseId).stream().map(StudentDto::getId)
                        .toList().contains(studentId))
                    throw new StudentNotHaveCourseException();
                int affectedRows = getRepository().setScore(session, studentId, courseId, score);
                if (affectedRows == 0)
                    throw new NotSuccessfulException();
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
