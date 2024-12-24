package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.entity.enums.Degree;
import org.example.entity.enums.Field;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher extends User{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //@NotBlank(message = "field can not be null or blank!")
    private Field field;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //@NotBlank(message = "degree can not be null or blank!")
    private Degree degree;


    @Column(name = "personal_code",
            unique = true, nullable = false, length = 8)
    @NotBlank(
            message = "personal_code can not be null or blank! ")
    @Size(min = 8, max = 8,
            message = "personal_code must have 8 digits! ")
    @Pattern(regexp = "^\\d{8}$",
            message = "invalid format for personal_code! ")
    private String personnelCode;


    @Override
    public String toString() {
        return super.toString() + ", Teacher{" +
                "field= " + field +
                ", degree= " + degree +
                ", personnelCode= '" + personnelCode + '\'' +
                '}';
    }


}
