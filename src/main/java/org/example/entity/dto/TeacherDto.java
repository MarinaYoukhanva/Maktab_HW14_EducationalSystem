package org.example.entity.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    String firstName;
    String lastName;
    String email;
    String personnelCode;
}