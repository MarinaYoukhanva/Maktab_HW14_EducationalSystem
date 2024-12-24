package org.example.view;

import org.example.base.config.ApplicationContext;
import org.example.exception.NotFoundException;
import org.example.service.*;
import org.example.service.Authentication.UserAuthentication;

import java.util.Scanner;

public class TeacherMenu {

    int choice;
    Scanner scString = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    TeacherService teacherService = ApplicationContext.getTeacherService();
    CourseService courseService = ApplicationContext.getCourseService();

    public void teacherMenu() {

        System.out.println("Login...");
        System.out.println("Please enter your personnel code: ");
        String personnelCode = scString.nextLine();
        System.out.println("Please enter your password: ");
        String password = scString.nextLine();
        try {
            teacherService.login(personnelCode, password);
            loggedInMenu();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            teacherMenu();
        }
    }

    public void loggedInMenu() {
        System.out.println("Welcome to the teacher menu");
        System.out.println("1.Show my courses");
        System.out.println("2.Change password");
        System.out.println("3.Logout");
        choice = scInt.nextInt();
        switch (choice) {
            case 1:
                try {
                    courseService.teacherCourses(UserAuthentication.getLoggedInUser().getId())
                            .forEach(System.out::println);
                    System.out.println("-----------------------------");
                    System.out.println("1.Show students of a course ");
                    System.out.println("2.Back to previous menu");
                    choice = scInt.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Please enter your course id: ");
                            Long courseId = scInt.nextLong();
                            try {
                                teacherService.courseStudentsForTeacher(UserAuthentication
                                                        .getLoggedInUser().getId(),
                                                courseId)
                                        .forEach(System.out::println);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                loggedInMenu();
                            }
                            System.out.println("---------------------------");
                            System.out.println("1.Set score for a student ");
                            System.out.println("2.Back to previous menu");
                            choice = scInt.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("Student id: ");
                                    Long studentId = scInt.nextLong();
                                    System.out.println("Score: ");
                                    Double score = scInt.nextDouble();
                                    try {
                                        teacherService.teacherSetsScore(UserAuthentication
                                                        .getLoggedInUser().getId(),
                                                studentId, courseId, score);
                                        System.out.println("Setting score was successful");
                                        loggedInMenu();
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        loggedInMenu();
                                    }
                                    break;
                                case 2:
                                    loggedInMenu();
                                    break;
                            }
                            break;
                        case 2:
                            loggedInMenu();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    loggedInMenu();
                }
                break;
            case 2:
                break;
            case 3:
                UserAuthentication.logout();
                break;
        }
    }
}
