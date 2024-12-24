package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Course;

import java.util.List;

public interface CourseRepository extends BaseRepository<Long, Course> {

    List<Course> availableCoursesForStudent();

    List<Course> teacherCourses(Long teacherId);
}
