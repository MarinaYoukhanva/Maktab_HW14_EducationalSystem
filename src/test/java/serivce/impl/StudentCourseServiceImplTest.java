package serivce.impl;

import org.assertj.core.api.Assertions;
import org.example.entity.dto.StudentDto;
import org.example.exception.StudentNotHaveCourseException;
import org.example.repository.StudentCourseRepository;
import org.example.service.impl.StudentCourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


public class StudentCourseServiceImplTest {

    StudentCourseRepository studentCourseRepository;
    StudentCourseServiceImpl studentCourseServiceImpl;

    List<StudentDto> sampleStudents = List.of(
            (new StudentDto(
            1L, "one", "one", "email1", "11")),
            (new StudentDto(
                    2L, "two", "two", "email2", "22")),
            (new StudentDto(
                    3L, "three", "three", "email3", "33"))
    );


    @BeforeEach
    public void setUp() {
        studentCourseRepository = Mockito.mock(StudentCourseRepository.class);
        studentCourseServiceImpl = new StudentCourseServiceImpl(studentCourseRepository);
    }

    @Test
    public void setScoreWhenStudentDoesNotHaveCourseTest(){
        Mockito.when(studentCourseRepository.courseStudents(Mockito.any()))
                .thenReturn(sampleStudents);
        Mockito.when(studentCourseRepository
                .setScore(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(1);
        Assertions.assertThatExceptionOfType(StudentNotHaveCourseException.class)
                .isThrownBy(() -> studentCourseServiceImpl
                        .setScore(0L, 0L, 0.0));
    }
//    @Test
//    public void setValidScoreTest(){
//        Mockito.when(studentCourseRepository.courseStudents(Mockito.any()))
//                .thenReturn(sampleStudents);
//        Mockito.when(studentCourseRepository
//                        .setScore(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any()))
//                .thenReturn(1);
//        studentCourseServiceImpl.setScore(1L, 0L, 0.0);
//        Assertions.assertThat()
//    }

}
