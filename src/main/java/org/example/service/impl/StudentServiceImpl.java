package org.example.service.impl;

import org.example.SessionFactoryInstance;
import org.example.base.config.ApplicationContext;
import org.example.base.service.BaseServiceImpl;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.entity.StudentCourseKey;
import org.example.entity.dto.StudentDto;
import org.example.exception.*;
import org.example.repository.StudentRepository;
import org.example.service.Authentication.UserAuthentication;
import org.example.service.StudentService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;


public class StudentServiceImpl extends BaseServiceImpl<Long, Student, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Student student, Student foundStudent) {
        foundStudent.setFirstName(student.getFirstName());
        foundStudent.setLastName(student.getLastName());
        foundStudent.setUsername(student.getUsername());
        foundStudent.setPassword(student.getPassword());
        foundStudent.setPhoneNumber(student.getPhoneNumber());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setStudentNumber(student.getStudentNumber());
    }

    @Override
    public void infoLogicCheck(Session session, Student student) {
        emailUniqueCheck(session, student);
        usernameUniqueCheck(session, student);
        nationalCodeUniqueCheck(session, student);
        studentNumberUniqueCheck(session, student);
    }

    @Override
    public Student login(String studentNumber, String password) {
        Student student = getRepository().findByStudentNumber(studentNumber)
                .orElseThrow(() -> new NotFoundException(Student.class));
        if (!student.getPassword().equals(password))
            throw new NotFoundException(Student.class);
        UserAuthentication.setLoggedInUser(student);
        return student;
    }

    @Override
    public List<StudentDto> findAllDto() {
        return findAll().stream().map(student -> new StudentDto(
                student.getId(),
                student.getFirstName(), student.getLastName(),
                Optional.ofNullable(student.getEmail())
                        .orElse("email is not available by user"),
                student.getStudentNumber())
        ).toList();
    }

    @Override
    public void takeCourse(Long studentId, Long courseId) {
        try (var session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Course course = ApplicationContext.getCourseRepository()
                        .findById(session, courseId)
                                .orElseThrow(() -> new NotFoundException(Course.class));
                Student student = getRepository()
                        .findById(session, studentId)
                                .orElseThrow(() -> new NotFoundException(Student.class));
                StudentCourse studentCourse = StudentCourse.builder()
                        .id(new StudentCourseKey(studentId, courseId))
                        .student(student)
                        .course(course)
                        .build();
                ApplicationContext.getStudentCourseRepository().save(session, studentCourse);
                course.setCapacity(course.getCapacity() - 1);
                ApplicationContext.getCourseRepository().save(session, course);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    private void usernameUniqueCheck(Session session, Student student) {
        Long usernameCount = getRepository().usernameExistence(session, student);
        if (usernameCount != null && usernameCount > 0)
            throw new UsernameExistsException();
    }

    private void emailUniqueCheck(Session session, Student student) {
        Long emailCount = getRepository().emailExistence(session, student);
        if (emailCount != null && emailCount > 0)
            throw new EmailExistsException();
    }

    private void nationalCodeUniqueCheck(Session session, Student student) {
        Long nationalCodeCount = getRepository().nationalCodeExistence(session, student);
        if (nationalCodeCount != null && nationalCodeCount > 0)
            throw new NationalCodeExistsException();
    }

    private void studentNumberUniqueCheck(Session session, Student student) {
        Long studentNumberCount = getRepository()
                .studentNumberExistence(session, student.getStudentNumber());
        if (studentNumberCount != null && studentNumberCount > 0)
            throw new StudentNumberExistsException();
    }


}
