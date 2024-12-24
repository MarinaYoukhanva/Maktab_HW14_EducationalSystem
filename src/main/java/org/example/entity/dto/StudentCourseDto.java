package org.example.entity.dto;

import lombok.*;
import org.example.entity.Teacher;
import org.example.entity.enums.LessonName;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDto {

    Long courseId;
    Long studentId;
    Long teacherId;
    LessonName name;
    Integer unit;
    LocalDateTime startTime;
    Double score;
    //Teacher teacher;
}
