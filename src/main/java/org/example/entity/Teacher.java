package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Field field;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(name = "personal_code",unique = true, nullable = false, length = 10)
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
