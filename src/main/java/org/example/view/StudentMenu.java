package org.example.view;

import org.example.base.config.ApplicationContext;
import org.example.entity.StudentCourse;
import org.example.exception.NotFoundException;
import org.example.service.*;
import org.example.service.Authentication.UserAuthentication;

import java.util.Scanner;

public class StudentMenu {

    int choice;
    Scanner scString = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();
    CourseService courseService = ApplicationContext.getCourseService();
    StudentCourseService studentCourseService = ApplicationContext.getStudentcourseService();

    public void studentMenu() {

        System.out.println("Login...");
        System.out.println("Please enter your student number: ");
        String studentNumber = scString.nextLine();
        System.out.println("Please enter your password: ");
        String password = scString.nextLine();

        try {
            studentService.login(studentNumber, password);
            loggedInMenu();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            studentMenu();
        }
    }

    public void loggedInMenu() {
        System.out.println("Welcome to Student Menu! ");
        System.out.println("1.Take new course");
        System.out.println("2.Show my courses");
        System.out.println("3.Logout");
        choice = scInt.nextInt();
        switch (choice) {
            case 1:
                courseService.availableCourses().forEach(System.out::println);
                System.out.println("Select s course: ");
                Long courseId = scInt.nextLong();
                try {
                    studentService.takeCourse(UserAuthentication.getLoggedInUser().getId(), courseId);
                    System.out.println("Taking course was successful ");
                    loggedInMenu();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    loggedInMenu();
                }
                break;
            case 2:
                studentCourseService.studentCourses(UserAuthentication.getLoggedInUser().getId())
                        .forEach(System.out::println);
                loggedInMenu();
                break;
            case 3:
                UserAuthentication.logout();
                break;
        }
    }
}
