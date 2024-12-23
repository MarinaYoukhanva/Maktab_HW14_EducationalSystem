package org.example.view;

import jakarta.validation.ValidationException;
import org.example.base.config.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.enums.Degree;
import org.example.entity.enums.Field;
import org.example.entity.enums.LessonName;
import org.example.exception.NotFoundException;
import org.example.service.AdminService;
import org.example.service.Authentication.AdminAuthentication;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.service.TeacherService;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Scanner;

public class AdminMenu {

    int choice;
    int secondChoice;
    Scanner scString = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();
    AdminService adminService = ApplicationContext.getAdminService();
    TeacherService teacherService = ApplicationContext.getTeacherService();
    CourseService courseService = ApplicationContext.getCourseService();

    public void adminMenu() {

        System.out.println("Login...");
        System.out.println("Please enter your username: ");
        String username = scString.nextLine();
        System.out.println("Please enter your password: ");
        String password = scString.nextLine();
        try {
            adminService.login(username, password);
            loggedInMenu();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    public void loggedInMenu() {
        System.out.println("Welcome to Admin Menu! ");
        System.out.println("1.Show all students");
        System.out.println("2.Show all teachers");
        System.out.println("3.show all courses");
        System.out.println("4.Logout");
        choice = scInt.nextInt();
        switch (choice) {
            case 1:
                studentService.findAllDto().forEach(System.out::println);
                studentMenu();
                break;
            case 2:
                teacherService.findAllDto().forEach(System.out::println);
                teacherMenu();
                break;
            case 3:
                courseService.findAll().forEach(System.out::println);
                courseMenu();
                break;
            case 4:
                AdminAuthentication.logout();
                break;

        }
    }

    public void studentMenu() {
        System.out.println("1.Add new student");
        System.out.println("2.Back to previous menu");
        secondChoice = scInt.nextInt();
        switch (secondChoice) {
            case 1:
                System.out.println("1.Firstname: ");
                String firstname = scString.nextLine();
                System.out.println("2.Lastname: ");
                String lastname = scString.nextLine();
                System.out.println("3.Phone-number ( start with 0098 or +98 ): ");
                String phone = scString.nextLine();
                System.out.println("4.National-code ( WARNING: it can not be updated! ): ");
                String nationalCode = scString.nextLine();
                System.out.println("5.Student-number: ");
                String studentNumber = scString.nextLine();
                Student student = Student.builder()
                        .firstName(firstname)
                        .lastName(lastname)
                        .phoneNumber(phone)
                        .nationalCode(nationalCode)
                        .studentNumber(studentNumber)
                        .username(studentNumber)
                        .password(nationalCode)
                        .build();
                try {
                    studentService.save(student);
                    System.out.println("Saving was successful");
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                    studentMenu();
                }
                break;
            case 2:
                studentMenu();
                break;
        }
    }

    public void teacherMenu() {
        System.out.println("1.Add new teacher");
        System.out.println("2.Delete teacher");
        System.out.println("3.Back to previous menu");
        secondChoice = scInt.nextInt();
        switch (secondChoice) {
            case 1:
                System.out.println("1.Firstname: ");
                String firstname = scString.nextLine();
                System.out.println("2.Lastname: ");
                String lastname = scString.nextLine();
                System.out.println("3.Phone-number ( start with 0098 or +98 ): ");
                String phone = scString.nextLine();
                System.out.println("4.National-code ( WARNING: it can not be updated! ): ");
                String nationalCode = scString.nextLine();
                System.out.println("5.Personnel-code: ");
                String personnelCode = scString.nextLine();
                System.out.println("6.Field: ");
                EnumSet.allOf(Field.class).forEach(System.out::println);
                String field = scString.nextLine();
                System.out.println("7.Degree: ");
                EnumSet.allOf(Degree.class).forEach(System.out::println);
                String degree = scString.nextLine();
                Teacher teacher = Teacher.builder()
                        .firstName(firstname)
                        .lastName(lastname)
                        .phoneNumber(phone)
                        .nationalCode(nationalCode)
                        .personnelCode(personnelCode)
                        .field(Field.valueOf(field.toUpperCase()))
                        .degree(Degree.valueOf(degree.toUpperCase()))
                        .username(personnelCode)
                        .password(nationalCode)
                        .build();
                try {
                    teacherService.save(teacher);
                    System.out.println("Saving was successful");
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                    teacherMenu();
                }
                break;
            case 2:
                break;
            case 3:
                adminMenu();
                break;
        }
    }

    public void courseMenu() {
        System.out.println("1.Add new course");
        System.out.println("2.Delete course");
        System.out.println("3.Edit course");
        System.out.println("4.Back to previous menu");
        secondChoice = scInt.nextInt();
        switch (secondChoice) {
            case 1:
                System.out.println("1.Course name: ");
                EnumSet.allOf(LessonName.class).forEach(System.out::println);
                String courseName = scString.nextLine();
                System.out.println("2.Unit: ");
                int unit = scInt.nextInt();
                System.out.println("3.Capacity: ");
                int capacity = scInt.nextInt();
                System.out.println("4.Start time (year, month, day, hour, minute): ");
                LocalDateTime startTime = LocalDateTime.of(scInt.nextInt(), scInt.nextInt(),
                        scInt.nextInt(), scInt.nextInt(), scInt.nextInt());
                Course course = Course.builder()
                        .name(LessonName.valueOf(courseName.toUpperCase()))
                        .unit(unit).capacity(capacity)
                        .startTime(startTime)
                        .build();
                try {
                    courseService.save(course);
                    System.out.println("Saving was successful");
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                    teacherMenu();
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                adminMenu();
                break;
        }
    }
}
