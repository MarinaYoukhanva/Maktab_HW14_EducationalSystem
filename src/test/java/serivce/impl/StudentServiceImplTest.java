package serivce.impl;

import jakarta.validation.ValidationException;
import org.assertj.core.api.Assertions;
import org.example.base.config.ApplicationContext;
import org.example.entity.*;
import org.example.entity.enums.LessonName;
import org.example.exception.EmailExistsException;
import org.example.exception.NotFoundException;
import org.example.repository.*;
import org.example.service.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;



public class StudentServiceImplTest {

    StudentRepository studentRepository;
    StudentServiceImpl studentServiceImpl;
    CourseRepository courseRepository;
    CourseServiceImpl courseServiceImpl;
    StudentCourseRepository studentCourseRepository;
    StudentCourseServiceImpl studentCourseServiceImpl;
    MockedStatic<ApplicationContext> mockedContext;

    Student validStudentForTest = Student.builder()
            .firstName("first")
            .lastName("last")
            .username("55555555")
            .password("password")
            .phoneNumber("+9809127778899")
            .nationalCode("1122334455")
            .email("test@gmail.com")
            .studentNumber("66666666")
            .build();
    Course sampleCourse = Course.builder()
            .name(LessonName.MATH1)
            .capacity(20)
            .unit(3)
            .startTime(LocalDateTime.now())
            .build();
    StudentCourse sampleStudentCourse = StudentCourse.builder()
            .course(sampleCourse)
            .student(validStudentForTest)
            .build();


//    @BeforeEach
//    public void setUpSession() {
//        SessionFactory mockSessionFactory = Mockito.mock(SessionFactory.class);
//        Session mockSession = Mockito.mock(Session.class);
//        Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
//
//        SessionFactoryInstance.sessionFactory = mockSessionFactory;
//
//        var sessionManager = Mockito.mock(SessionManager.class);
//        Mockito.when(sessionManager.executeWithinTransaction(Mockito.any()))
//                .thenAnswer(invocation -> {
//                    Function<Session, ?> action = invocation.getArgument(0);
//                    var session = Mockito.mock(Session.class);
//                    return action.apply(session);
//                });
//    }

    @BeforeEach
    public void setUpApplicationContext() {
        mockedContext = mockStatic(ApplicationContext.class) ;

            studentRepository = Mockito.mock(StudentRepository.class);
            courseRepository = Mockito.mock(CourseRepository.class);
            studentCourseRepository = Mockito.mock(StudentCourseRepository.class);

            mockedContext.when(ApplicationContext::getCourseRepository).thenReturn(courseRepository);
            mockedContext.when(ApplicationContext::getStudentRepository).thenReturn(studentRepository);
            mockedContext.when(ApplicationContext::getStudentCourseRepository).thenReturn(studentCourseRepository);

            courseServiceImpl = new CourseServiceImpl(courseRepository);
            studentServiceImpl = new StudentServiceImpl(studentRepository);
            studentCourseServiceImpl = new StudentCourseServiceImpl(studentCourseRepository);
    }
    @AfterEach
    public void tearDown() {
        mockedContext.close();
    }

    @Test
    public void saveTestWithValidInfo() {
        Student student = studentServiceImpl.save(validStudentForTest);
        Mockito.when(studentRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(student);
        Assertions.assertThat(student.getFirstName()).isEqualTo("first");
        Assertions.assertThat(student.getLastName()).isEqualTo("last");
        Assertions.assertThat(student.getUsername()).isEqualTo("55555555");
        Assertions.assertThat(student.getPassword()).isEqualTo("password");
        Assertions.assertThat(student.getPhoneNumber()).isEqualTo("+9809127778899");
        Assertions.assertThat(student.getNationalCode()).isEqualTo("1122334455");
        Assertions.assertThat(student.getEmail()).isEqualTo("test@gmail.com");
        Assertions.assertThat(student.getStudentNumber()).isEqualTo("66666666");
    }

    @Test
    public void saveTestWithInvalidInfo() {
        var studentForTest = Student.builder()
                .firstName("first")
                .lastName("last")
                .username("55555555")
                .password("password")
                .phoneNumber("+1234")
                .nationalCode("1122")
                .email("test")
                .studentNumber("66666666")
                .build();
        Mockito.when(studentRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(studentForTest);
        Assertions.assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> studentServiceImpl.save(studentForTest))
                .withMessageContaining("invalid email format! ");
    }

    @Test
    public void saveTestWithUniquenessIssue() {
        Mockito.when(studentRepository.emailExistence(Mockito.any(), Mockito.any()))
                .thenReturn(1L);
        Mockito.when(studentRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(validStudentForTest);
        Assertions.assertThatExceptionOfType(EmailExistsException.class)
                .isThrownBy(() -> studentServiceImpl.save(validStudentForTest));
    }

    @Test
    public void loginTestWithWrongPassword() {
        Mockito.when(studentRepository.findByStudentNumber(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(validStudentForTest));
        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> studentServiceImpl
                        .login(validStudentForTest.getStudentNumber(), "wrongPass"));
    }

    @Test
    public void loginTestWithCorrectPassword() {
        Mockito.when(studentRepository.findByStudentNumber(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(validStudentForTest));
        Student student = studentServiceImpl
                .login(validStudentForTest.getStudentNumber(), "password");
        Assertions.assertThat(student.getFirstName()).isEqualTo("first");
    }

    @Test
    public void takeCourseTest() {
        Mockito.when(courseRepository.findById(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.ofNullable(sampleCourse));
        Mockito.when(studentRepository.findById(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.ofNullable(validStudentForTest));
        Mockito.when(studentCourseRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(sampleStudentCourse);
        Mockito.when(courseRepository.save(Mockito.any(), Mockito.any()))
                .thenReturn(sampleCourse);
        studentServiceImpl.takeCourse(0L, 0L);
        Assertions.assertThat(sampleCourse.getCapacity()).isEqualTo(19);
    }
}
