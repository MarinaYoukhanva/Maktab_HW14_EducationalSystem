package org.example.entity.dto;

import lombok.*;
import org.example.entity.enums.Degree;
import org.example.entity.enums.Field;

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
    Field field;
    Degree degree;
}