package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Course;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface CourseRepository extends BaseRepository<Long, Course> {

    List<Course> availableCourses();
}
