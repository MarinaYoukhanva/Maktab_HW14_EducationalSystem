package org.example.entity;

import jakarta.persistence.Column;
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

    @Column(nullable = false, unique = true, length = 100)
    private LessonName name;

    @Column(nullable = false, length = 2)
    private Integer unit;

    @Column(nullable = false, length = 3)
    private Integer capacity;

    @Column(name = "start_time", nullable = false, length = 50)
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
