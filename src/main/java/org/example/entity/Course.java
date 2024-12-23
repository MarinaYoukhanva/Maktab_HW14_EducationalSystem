package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.base.model.BaseEntity;
import org.example.entity.enums.LessonName;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course extends BaseEntity<Long> {

    @Column(
            nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
//    @NotBlank(
//            message = "course-name can not be null or blank! ")
    private LessonName name;


    @Column(
            nullable = false, length = 2)
//    @NotBlank(
//            message = "unit can not be null or blank! ")
//    @Size(max = 2,
//            message = "long number! ")
    private Integer unit;


    @Column(
            nullable = false, length = 3)
//    @NotBlank(
//            message = "capacity can not be null or blank! ")
//    @Size(max = 3,
//            message = "long number! ")
    private Integer capacity;


    @Column(name = "start_time",
            nullable = false, length = 50)
//    @NotBlank(
//            message = "start_time can not be null or blank! ")
//    @Size(max = 50,
//            message = "long input for start_time! ")
    private LocalDateTime startTime;


    @ManyToOne
    private Teacher teacher;


    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                "name=" + name +
                ", unit=" + unit +
                ", capacity=" + capacity +
                ", startTime=" + startTime +
                '}';
    }
}
