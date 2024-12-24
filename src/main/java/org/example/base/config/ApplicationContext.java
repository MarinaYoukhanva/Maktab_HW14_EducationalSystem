package org.example.base.config;

import org.example.entity.*;
import org.example.repository.*;
import org.example.repository.impl.*;
import org.example.service.*;
import org.example.service.impl.*;

public class ApplicationContext {
    private static final Class<Student> studentClass;
    private static final StudentRepository STUDENTREPOSITORY;
    private static final StudentService STUDENTSERVICE;

    private static final Class<Admin> adminClass;
    private static final AdminRepository ADMINREPOSITORY;
    private static final AdminService ADMINSERVICE;

    private static final Class<Teacher> teacherClass;
    private static final TeacherRepository TEACHERREPOSITORY;
    private static final TeacherService TEACHERSERVICE;

    private static final Class<Course> courseClass;
    private static final CourseRepository COURSEREPOSITORY;
    private static final CourseService COURSESERVICE;

    private static final StudentCourseRepository STUDENTCOURSEREPOSITORY;
    private static final StudentCourseService STUDENTCOURSESERVICE;


    static {
        studentClass = Student.class;
        STUDENTREPOSITORY = new StudentRepositoryImpl(studentClass);
        STUDENTSERVICE = new StudentServiceImpl(STUDENTREPOSITORY);

        adminClass = Admin.class;
        ADMINREPOSITORY = new AdminRepositoryImpl(adminClass);
        ADMINSERVICE = new AdminServiceImpl(ADMINREPOSITORY);

        teacherClass = Teacher.class;
        TEACHERREPOSITORY = new TeacherRepositoryImpl(teacherClass);
        TEACHERSERVICE = new TeacherServiceImpl(TEACHERREPOSITORY);

        courseClass = Course.class;
        COURSEREPOSITORY = new CourseRepositoryImpl(courseClass);
        COURSESERVICE = new CourseServiceImpl(COURSEREPOSITORY);

        STUDENTCOURSEREPOSITORY = new StudentCourseRepositoryImpl();
        STUDENTCOURSESERVICE = new StudentCourseServiceImpl(STUDENTCOURSEREPOSITORY);


    }
    public static StudentService getStudentService() {
        return STUDENTSERVICE;
    }
    public static AdminService getAdminService() {return ADMINSERVICE; }
    public static TeacherService getTeacherService() {return TEACHERSERVICE; }
    public static CourseService getCourseService() {return COURSESERVICE; }
    public static StudentCourseService getStudentcourseService() {return STUDENTCOURSESERVICE; }


    public static TeacherRepository getTeacherRepository() {return TEACHERREPOSITORY; }
    public static CourseRepository getCourseRepository() {return COURSEREPOSITORY; }
    public static StudentRepository getStudentRepository() {return STUDENTREPOSITORY; }
    public static StudentCourseRepository getStudentCourseRepository() {return STUDENTCOURSEREPOSITORY; }

}
