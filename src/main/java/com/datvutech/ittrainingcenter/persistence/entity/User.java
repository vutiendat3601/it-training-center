package com.datvutech.ittrainingcenter.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.datvutech.ittrainingcenter.persistence.type.UserRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String userUuid;

    private String firstName;

    private String lastName;

    private String email;

    private String pwd;

    private String emailVerificationCode;

    private boolean emailVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_id")
    private UserRole role;
}
