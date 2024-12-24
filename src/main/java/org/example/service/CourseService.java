package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Course;
import org.hibernate.Session;

import java.util.List;

public interface CourseService extends BaseService<Long, Course> {

    public Course setTeacher(Long courseId, Long teacherId);

    List<Course> availableCourses();

    List<Course> teacherCourses(Long teacherId);
}
