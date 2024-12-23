package org.example.view;

import jakarta.validation.ValidationException;
import org.example.base.config.ApplicationContext;
import org.example.entity.Student;
import org.example.exception.NotFoundException;
import org.example.service.AdminService;
import org.example.service.StudentService;

import java.util.Scanner;

public class AdminMenu {

    int choice;
    int secondChoice;
    Scanner sc = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();
    AdminService adminService = ApplicationContext.getAdminService();

    public void adminMenu() {

        System.out.println("Login...");
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
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
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                studentService.findAllDto().forEach(System.out::println);
                studentMenu();
                break;
            case 2:
                System.out.println("1.Add new teacher");
                System.out.println("2.Delete teacher");
                System.out.println("3.Back to previous menu");
                secondChoice = sc.nextInt();
                switch (secondChoice) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        adminMenu();
                        break;
                }
                break;
            case 3:
                System.out.println("1.Add new course");
                System.out.println("2.Delete course");
                System.out.println("3.Edit course");
                System.out.println("4.Back to previous menu");
                secondChoice = sc.nextInt();
                switch (secondChoice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        adminMenu();
                        break;
                }
                break;
            case 4:
                break;

        }
    }

    public void studentMenu() {
        System.out.println("1.Add new student");
        System.out.println("2.Back to previous menu");
        secondChoice = sc.nextInt();
        switch (secondChoice) {
            case 1:
                System.out.println("1.Firstname: ");
                String firstname = sc.nextLine();
                System.out.println("2.Lastname: ");
                String lastname = sc.nextLine();
                System.out.println("3.Phone-number ( start with 0098 or +98 ): ");
                String phone = sc.nextLine();
                System.out.println("4.National-code ( WARNING: it can not be updated! ): ");
                String nationalCode = sc.nextLine();
                System.out.println("5.Student-number: ");
                String studentNumber = sc.nextLine();
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
}
