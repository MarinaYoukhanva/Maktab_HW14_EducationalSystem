package org.example.entity.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    Long id;
    String firstName;
    String lastName;
    String email;
    String studentNumber;
}
