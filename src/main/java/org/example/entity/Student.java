package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User{

    @Column(name = "student_number",unique = true, nullable = false, length = 8)
    @Pattern(regexp = "^\\d{8}$",
            message = "invalid format or size for student-number! ")
    private String studentNumber;

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }

}
