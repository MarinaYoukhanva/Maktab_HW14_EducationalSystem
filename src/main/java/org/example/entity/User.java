package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String nationalCode;

    public User(Long id,
                String firstName, String lastName, String username, String password,
                String phoneNumber, String email, String nationalCode) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationalCode = nationalCode;
    }

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
