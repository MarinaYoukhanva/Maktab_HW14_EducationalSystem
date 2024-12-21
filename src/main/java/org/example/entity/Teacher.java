package org.example.entity;

import jakarta.persistence.Entity;
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

    private Field field;
    private Degree degree;
    private String personnelCode;

    @Override
    public String toString() {
        return super.toString() + ", Teacher{" +
                "field=" + field +
                ", degree=" + degree +
                ", personnelCode='" + personnelCode + '\'' +
                '}';
    }


}
