package org.example.service.impl;

import org.example.base.config.ApplicationContext;
import org.example.base.service.BaseServiceImpl;
import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.entity.dto.StudentDto;
import org.example.entity.dto.TeacherDto;
import org.example.exception.*;
import org.example.repository.TeacherRepository;
import org.example.service.Authentication.UserAuthentication;
import org.example.service.TeacherService;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl extends BaseServiceImpl<Long, Teacher, TeacherRepository>
        implements TeacherService {
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Teacher teacher, Teacher foundTeacher) {
        foundTeacher.setFirstName(teacher.getFirstName());
        foundTeacher.setLastName(teacher.getLastName());
        foundTeacher.setUsername(teacher.getUsername());
        foundTeacher.setPassword(teacher.getPassword());
        foundTeacher.setPhoneNumber(teacher.getPhoneNumber());
        foundTeacher.setEmail(teacher.getEmail());
        foundTeacher.setPersonnelCode(teacher.getPersonnelCode());
        foundTeacher.setField(teacher.getField());
        foundTeacher.setDegree(teacher.getDegree());
    }

    @Override
    public void infoLogicCheck(Session session, Teacher teacher) {
        usernameUniqueCheck(session, teacher);
        emailUniqueCheck(session, teacher);
        nationalCodeUniqueCheck(session, teacher);
        personnelCodeUniqueCheck(session, teacher);
    }

    @Override
    public Teacher login(String personnelCode, String password) {
        Teacher teacher = getRepository().findByPersonnelCode(personnelCode)
                .orElseThrow(() -> new NotFoundException(Teacher.class));
        if (!password.equals(teacher.getPassword()))
            throw new NotFoundException(Teacher.class);
        UserAuthentication.setLoggedInUser(teacher);
        return teacher;
    }

    @Override
    public List<TeacherDto> findAllDto() {
        return findAll().stream()
                .map(teacher -> new TeacherDto(
                        teacher.getId(),
                        teacher.getFirstName(), teacher.getLastName(),
                        Optional.ofNullable(teacher.getEmail())
                                .orElse("email is not available by user"),
                        teacher.getPersonnelCode(),
                        teacher.getField(),
                        teacher.getDegree())
                ).toList();

    }

    @Override
    public List<StudentDto> courseStudentsForTeacher(Long teacherId, Long courseId) {
        teacherHasCourseCheck(teacherId, courseId);
        return ApplicationContext.getStudentcourseService().courseStudents(courseId);
    }
    @Override
    public void teacherSetsScore(Long teacherId, Long studentId, Long courseId, Double score){
        Course course = teacherHasCourseCheck(teacherId, courseId);
        if (course.getStartTime().isAfter(LocalDateTime.now()))
            throw new CourseNotStartException();
        ApplicationContext.getStudentcourseService().setScore(studentId, courseId, score);
    }

    private void usernameUniqueCheck(Session session, Teacher teacher) {
        Long usernameCount = getRepository().usernameExistence(session, teacher);
        if (usernameCount != null && usernameCount > 0)
            throw new UsernameExistsException();
    }

    private void emailUniqueCheck(Session session, Teacher teacher) {
        Long emailCount = getRepository().emailExistence(session, teacher);
        if (emailCount != null && emailCount > 0)
            throw new EmailExistsException();
    }

    private void nationalCodeUniqueCheck(Session session, Teacher teacher) {
        Long nationalCodeCount = getRepository().nationalCodeExistence(session, teacher);
        if (nationalCodeCount != null && nationalCodeCount > 0)
            throw new NationalCodeExistsException();
    }

    private void personnelCodeUniqueCheck(Session session, Teacher teacher) {
        Long personnelCodeCount = getRepository()
                .personnelCodeExistence(session, teacher.getPersonnelCode());
        if (personnelCodeCount != null && personnelCodeCount > 0)
            throw new PersonnelCodeExistsException();
    }
    private Course teacherHasCourseCheck(Long teacherId, Long courseId){
        Course course = ApplicationContext.getCourseService().findById(courseId)
                .orElseThrow(() -> new NotFoundException(Course.class));
        if (course.getTeacher() == null || !course.getTeacher().getId().equals(teacherId))
            throw new TeacherNotHaveCourseException();
        return course;
    }
}
