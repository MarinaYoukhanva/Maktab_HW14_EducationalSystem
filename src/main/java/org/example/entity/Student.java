package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User{

    @Column(name = "student_number",unique = true, nullable = false, length = 10)
    private String studentNumber;

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }

}
