package org.example.view;

import jakarta.validation.ValidationException;
import org.example.base.config.ApplicationContext;
import org.example.entity.Student;
import org.example.service.StudentService;

import java.util.Scanner;

public class AdminMenu {

    int choice;
    int secondChoice;
    Scanner sc = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();

    public void adminMenu() {

        System.out.println("Login");

        System.out.println("1.Show all students");
        System.out.println("2.Show all teachers");
        System.out.println("3.show all courses");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("1.Add new student");
                System.out.println("2.Back to previous menu");
                secondChoice = sc.nextInt();
                switch (secondChoice) {
                    case 1:
                        System.out.println("1.Firstname: ");
                        String firstname = sc.next();
                        System.out.println("2.Lastname: ");
                        String lastname = sc.next();
                        System.out.println("3.Phone-number ( start with 0098 or +98 ): ");
                        String phone = sc.next();
                        System.out.println("4.National-code ( WARNING: it can not be updated! ): ");
                        String nationalCode = sc.next();
                        System.out.println("5.Student-number: ");
                        String studentNumber = sc.next();
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
                            System.out.println("hii after exception");
                        }
                        break;
                    case 2:
                        adminMenu();
                        break;
                }
                break;
            case 2:
                System.out.println("1.Add new teacher");
                System.out.println("2.Back to previous menu");
                secondChoice = sc.nextInt();
                switch (secondChoice) {
                    case 1:
                        break;
                    case 2:
                        adminMenu();
                        break;
                }
                break;
            case 3:
                System.out.println("1.Add new course");
                System.out.println("2.Back to previous menu");
                secondChoice = sc.nextInt();
                switch (secondChoice) {
                    case 1:
                        break;
                    case 2:
                        adminMenu();
                        break;
                }
                break;

        }

    }
}
