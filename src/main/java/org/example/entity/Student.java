package org.example.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User{

    private String studentNumber;

    public Student(Long id,
                   String firstName, String lastName, String username, String password,
                   String phoneNumber, String email, String nationalCode,
                   String studentNumber) {
        super(id, firstName, lastName, username, password, phoneNumber, email, nationalCode);
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                '}';
    }

}
