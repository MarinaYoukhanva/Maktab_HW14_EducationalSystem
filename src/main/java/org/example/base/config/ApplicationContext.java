package org.example.base.config;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.repository.StudentRepositoryImpl;
import org.example.service.StudentService;
import org.example.service.StudentServiceImpl;

public class ApplicationContext {
    private static final Class<Student> studentClass;
    private static final StudentRepository STUDENTREPOSITORY;
    private static final StudentService STUDENTSERVICE;

    static {
        studentClass = Student.class;
        STUDENTREPOSITORY = new StudentRepositoryImpl(studentClass);
        STUDENTSERVICE = new StudentServiceImpl(STUDENTREPOSITORY);

    }
    public static StudentService getStudentService() {
        return STUDENTSERVICE;
    }

}
