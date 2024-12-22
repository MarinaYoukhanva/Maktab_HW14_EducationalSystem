package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.base.model.BaseEntity;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {

//    public User(Long id,
//                String firstName, String lastName, String username, String password,
//                String email, String phoneNumber, String nationalCode) {
//        super(id);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.nationalCode = nationalCode;
//    }

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "firstname can not be blank! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "firstname only can contains letters! ")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "lastname can not be blank! ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastname only can contains letters! ")
    private String lastName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 50)
    @Pattern(regexp = "^00980?9|\\+980?9[01239]\\d{8}$", message = "invalid phone-number! ")
    private String phoneNumber;

    @Column(unique = true, length = 100)
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format! ")
    private String email;

    @Column(name = "national_code", nullable = false, unique = true, length = 10, updatable = false)
    @Pattern(regexp = "^\\d{10}$", message = "invalid format or size for national-code! ")
    private String nationalCode;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }


}
