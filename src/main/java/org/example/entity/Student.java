package org.example.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User{

    private String studentNumber;

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }

}
