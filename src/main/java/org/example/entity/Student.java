package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User{

    @Column(name = "student_number",
            unique = true, nullable = false, length = 8)
    @NotBlank(
            message = "student_number can not be null or blank! ")
    @Size(min = 8, max = 8,
            message = "student_number must have 8 digits! ")
    @Pattern(regexp = "^\\d{8}$",
            message = "invalid format for student-number! ")
    private String studentNumber;


    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }

}
