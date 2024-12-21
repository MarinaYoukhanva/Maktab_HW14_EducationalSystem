package org.example.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.enums.Degree;
import org.example.entity.enums.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher extends User{

    private Field field;
    private Degree degree;
    private String personnelCode;


    public Teacher(Long id,
                   String firstName, String lastName, String username, String password,
                   String phoneNumber, String email, String nationalCode,
                   Field field, Degree degree, String personnelCode) {
        super(id, firstName, lastName, username, password, phoneNumber, email, nationalCode);
        this.field = field;
        this.degree = degree;
        this.personnelCode = personnelCode;
    }

    @Override
    public String toString() {
        return super.toString() + ", Teacher{" +
                "field=" + field +
                ", degree=" + degree +
                ", personnelCode='" + personnelCode + '\'' +
                '}';
    }


}
