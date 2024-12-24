package org.example.view;

import java.util.Scanner;

public class MainMenu {

    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        AdminMenu adminMenu = new AdminMenu();
        TeacherMenu teacherMenu = new TeacherMenu();
        StudentMenu studentMenu = new StudentMenu();

        System.out.println("1.Admin");
        System.out.println("2.Teacher");
        System.out.println("3.Student");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                adminMenu.adminMenu();
                break;
            case 2:
                teacherMenu.teacherMenu();
                break;
            case 3:
                studentMenu.studentMenu();
                break;
        }
    }
}
