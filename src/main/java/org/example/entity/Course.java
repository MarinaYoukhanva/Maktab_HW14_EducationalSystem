package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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

    private LessonName name;
    private Integer unit;
    private Integer capacity;
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
