package org.example.base.config;

import org.example.entity.Admin;
import org.example.entity.Student;
import org.example.repository.AdminRepository;
import org.example.repository.StudentRepository;
import org.example.repository.impl.AdminRepositoryImpl;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.AdminService;
import org.example.service.StudentService;
import org.example.service.impl.AdminServiceImpl;
import org.example.service.impl.StudentServiceImpl;

public class ApplicationContext {
    private static final Class<Student> studentClass;
    private static final StudentRepository STUDENTREPOSITORY;
    private static final StudentService STUDENTSERVICE;

    private static final Class<Admin> adminClass;
    private static final AdminRepository ADMINREPOSITORY;
    private static final AdminService ADMINSERVICE;

    static {
        studentClass = Student.class;
        STUDENTREPOSITORY = new StudentRepositoryImpl(studentClass);
        STUDENTSERVICE = new StudentServiceImpl(STUDENTREPOSITORY);

        adminClass = Admin.class;
        ADMINREPOSITORY = new AdminRepositoryImpl(adminClass);
        ADMINSERVICE = new AdminServiceImpl(ADMINREPOSITORY);

    }
    public static StudentService getStudentService() {
        return STUDENTSERVICE;
    }
    public static AdminService getAdminService() {return ADMINSERVICE; }

}
