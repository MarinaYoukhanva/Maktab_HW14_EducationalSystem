package org.example.view;

import org.example.base.config.ApplicationContext;
import org.example.service.AdminService;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.service.TeacherService;

import java.util.Scanner;

public class TeacherMenu {

    int choice;
    int secondChoice;
    Scanner scString = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    StudentService studentService = ApplicationContext.getStudentService();
    AdminService adminService = ApplicationContext.getAdminService();
    TeacherService teacherService = ApplicationContext.getTeacherService();
    CourseService courseService = ApplicationContext.getCourseService();
}
