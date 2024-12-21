package org.example;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {

    public static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

    }

}
