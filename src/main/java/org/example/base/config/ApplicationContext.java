package org.example.base.config;

import org.example.entity.Admin;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.repository.AdminRepository;
import org.example.repository.CourseRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.impl.AdminRepositoryImpl;
import org.example.repository.impl.CourseRepositoryImpl;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.repository.impl.TeacherRepositoryImpl;
import org.example.service.AdminService;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.service.TeacherService;
import org.example.service.impl.AdminServiceImpl;
import org.example.service.impl.CourseServiceImpl;
import org.example.service.impl.StudentServiceImpl;
import org.example.service.impl.TeacherServiceImpl;

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
    private static final CourseRepository COURREPOSITORY;
    private static final CourseService COURSERSERVICE;

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
        COURREPOSITORY = new CourseRepositoryImpl(courseClass);
        COURSERSERVICE = new CourseServiceImpl(COURREPOSITORY);

    }
    public static StudentService getStudentService() {
        return STUDENTSERVICE;
    }
    public static AdminService getAdminService() {return ADMINSERVICE; }
    public static TeacherService getTeacherService() {return TEACHERSERVICE; }
    public static CourseService getCourseService() {return COURSERSERVICE; }

}
