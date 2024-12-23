package org.example;

import org.example.base.config.ApplicationContext;
import org.example.entity.Admin;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.entity.enums.Field;
import org.example.service.AdminService;
import org.example.service.StudentService;
import org.example.view.AdminMenu;
import org.hibernate.Session;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {

        Session session = SessionFactoryInstance.sessionFactory.openSession();

        //System.out.println(find(session, 1));
        StudentService studentService = ApplicationContext.getStudentService();
        AdminService adminService = ApplicationContext.getAdminService();
        AdminMenu adminMenu = new AdminMenu();
//        Student student = ss.save(new Student(
//                null, "m", "m", "m"));
//        System.out.println(student);

        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        //ss.save(student);

//        try {
//            studentService.save(Student.builder()
//                    .firstName(" ")
//                    .lastName(" ")
//                    .build());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            ;
//        }

        //System.out.println(adminService.findById(1L).get());
        EnumSet.allOf(Field.class).forEach(System.out::println);

        adminMenu.adminMenu();
    }

    public static Student find(Session session, Integer id) {
        return session.byId(Student.class)
                .load(id);
    }
    //Admin admin = new Admin(null, );


}