package org.example.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.model.BaseEntity;
import org.example.entity.enums.LessonName;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course extends BaseEntity<Long> {

    private LessonName name;
    private Integer unit;
    private Integer capacity;
    private LocalDateTime startTime;

    //private Teacher teacher;


    public Course(Long id,
                  LessonName name, Integer unit, Integer capacity,
                  LocalDateTime startTime) {
        super(id);
        this.name = name;
        this.unit = unit;
        this.capacity = capacity;
        this.startTime = startTime;
    }

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
