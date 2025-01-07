package serivce.impl;

import org.assertj.core.api.Assertions;
import org.example.base.config.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.enums.LessonName;
import org.example.exception.CourseNotStartException;
import org.example.exception.TeacherNotHaveCourseException;
import org.example.repository.CourseRepository;
import org.example.repository.StudentCourseRepository;
import org.example.repository.TeacherRepository;
import org.example.service.impl.CourseServiceImpl;
import org.example.service.impl.StudentCourseServiceImpl;
import org.example.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;

public class TeacherServiceImplTest {

    TeacherRepository teacherRepository;
    TeacherServiceImpl teacherServiceImpl;
    CourseRepository courseRepository;
    CourseServiceImpl courseServiceImpl;
    StudentCourseRepository studentCourseRepository;
    StudentCourseServiceImpl studentCourseServiceImpl;
    MockedStatic<ApplicationContext> mockedContext;

    Course sampleCourse = Course.builder()
            .name(LessonName.MATH1)
            .capacity(20)
            .unit(3)
            .startTime(LocalDateTime.now().plusDays(3L))
            .build();

    @BeforeEach
    public void setUpApplicationContext() {
        mockedContext = mockStatic(ApplicationContext.class) ;

        teacherRepository = Mockito.mock(TeacherRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        studentCourseRepository = Mockito.mock(StudentCourseRepository.class);

        mockedContext.when(ApplicationContext::getCourseRepository).thenReturn(courseRepository);
        mockedContext.when(ApplicationContext::getStudentCourseRepository).thenReturn(studentCourseRepository);

        courseServiceImpl = new CourseServiceImpl(courseRepository);
        studentCourseServiceImpl = new StudentCourseServiceImpl(studentCourseRepository);
        teacherServiceImpl = new TeacherServiceImpl(teacherRepository);
    }
    @AfterEach
    public void tearDown() {
        mockedContext.close();
    }

//    @Test
//    public void teacherSetScoreTest(){
//        Mockito.when(courseServiceImpl.findById(Mockito.anyLong()))
//                .thenReturn(Optional.ofNullable(sampleCourse));
//        Assertions.assertThatExceptionOfType(CourseNotStartException.class)
//                .isThrownBy(() -> teacherServiceImpl
//                        .teacherSetsScore(0L, 0L, 0L , 0.0));
//    }
}
