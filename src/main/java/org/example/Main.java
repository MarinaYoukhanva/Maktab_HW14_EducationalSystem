package org.example;

import org.example.base.config.ApplicationContext;
import org.example.entity.Admin;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.service.StudentService;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Session session = SessionFactoryInstance.sessionFactory.openSession();

        //System.out.println(find(session, 1));
        StudentService ss = ApplicationContext.getStudentService();
//        Student student = ss.save(new Student(
//                null, "m", "m", "m"));
//        System.out.println(student);

        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        ss.save(student);

    }
    public static Student find(Session session, Integer id) {
        return session.byId(Student.class)
                .load(id);
    }
    //Admin admin = new Admin(null, );


}