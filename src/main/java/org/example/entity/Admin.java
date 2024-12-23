package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.base.model.BaseEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends BaseEntity<Long> {

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

}
