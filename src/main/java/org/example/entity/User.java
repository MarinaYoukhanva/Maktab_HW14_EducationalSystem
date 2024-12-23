package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.base.model.BaseEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity<Long> {


    @Column(name = "first_name",
            nullable = false, length = 50)
    @NotBlank(
            message = "firstname can not be null or blank! ")
    @Size(max = 50,
            message = "long firstname! ")
    @Pattern(regexp = "^[a-zA-Z]+$",
            message = "firstname only can contains letters! ")
    private String firstName;


    @Column(name = "last_name",
            nullable = false, length = 50)
    @NotBlank(
            message = "lastname can not be null or blank! ")
    @Size(max = 50,
            message = "long lastname! ")
    @Pattern(regexp = "^[a-zA-Z]+$",
            message = "lastname only can contains letters! ")
    private String lastName;


    @Column(
            nullable = false, unique = true, length = 50)
    @NotBlank(
            message = "username can not be null or blank! ")
    @Size(max = 50,
            message = "long username! ")
    private String username;


    @Column(
            nullable = false, length = 50)
    @NotBlank(
            message = "password can not be null or blank! ")
    @Size(min = 8, max = 50,
            message = "password must have 8-50 characters! ")
    private String password;


    @Column(name = "phone_number",
            nullable = false, length = 15)
    @NotBlank(
            message = "phone_number can not be null or blank! ")
    @Size(max = 15,
            message = "long phone_number! ")
    @Pattern(regexp = "^00980?9|\\+980?9[01239]\\d{8}$",
            message = "invalid phone-number! ")
    private String phoneNumber;


    @Column(
            unique = true, length = 100)
    @Size(max = 100,
            message = "long email! ")
    @Pattern(regexp = "^[^@]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "invalid email format! ")
    private String email;


    @Column(name = "national_code",
            nullable = false, unique = true, length = 10, updatable = false)
    @NotBlank(
            message = "national_code can not be null or blank! ")
    @Pattern(regexp = "^\\d{10}$",
            message = "invalid format for national-code! ")
    @Size(min = 10, max = 10,
            message = "national-code must have 10 digits! ")
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
