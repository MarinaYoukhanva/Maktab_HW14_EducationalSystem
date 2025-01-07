package serivce.impl;

import org.assertj.core.api.Assertions;
import org.example.base.config.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.entity.enums.Degree;
import org.example.entity.enums.Field;
import org.example.entity.enums.LessonName;
import org.example.repository.CourseRepository;
import org.example.repository.TeacherRepository;
import org.example.service.impl.CourseServiceImpl;
import org.example.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;

public class CourseServiceImplTest {

    TeacherRepository teacherRepository;
    TeacherServiceImpl teacherServiceImpl;
    CourseRepository courseRepository;
    CourseServiceImpl courseServiceImpl;
    MockedStatic<ApplicationContext> mockedContext;

    Teacher sampleTeacher = Teacher.builder()
            .firstName("firstName")
            .lastName("lastName")
            .username("88888888")
            .password("password")
            .email("teacher@gmail.com")
            .phoneNumber("+989128889900")
            .nationalCode("0009998888")
            .personnelCode("88888888")
            .field(Field.COMPUTER)
            .degree(Degree.PROFESSORSHIP)
            .build();

    Course sampleCourse = Course.builder()
            .name(LessonName.MATH1)
            .capacity(20)
            .unit(3)
            .startTime(LocalDateTime.now())
            .build();

    @BeforeEach
    public void setUpApplicationContext() {
        mockedContext = mockStatic(ApplicationContext.class) ;

        teacherRepository = Mockito.mock(TeacherRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);

        mockedContext.when(ApplicationContext::getCourseRepository).thenReturn(courseRepository);
        mockedContext.when(ApplicationContext::getTeacherRepository).thenReturn(teacherRepository);

        courseServiceImpl = new CourseServiceImpl(courseRepository);
        teacherServiceImpl = new TeacherServiceImpl(teacherRepository);
    }
    @AfterEach
    public void tearDown() {
        mockedContext.close();
    }

    @Test
    public void setTeacherTest(){
        Mockito.when(courseRepository.findById(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.ofNullable(sampleCourse));
        Mockito.when(teacherRepository.findById(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.ofNullable(sampleTeacher));
        Mockito.when(courseRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(sampleCourse);
        courseServiceImpl.setTeacher(0L,0L);
        Assertions.assertThat(sampleCourse.getTeacher().getFirstName())
                .isEqualTo(sampleTeacher.getFirstName());
        Assertions.assertThat(sampleCourse.getTeacher())
                .isEqualTo(sampleTeacher);

    }
}
