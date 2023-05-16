package com.datvutech.ittrainingcenter.persistence.view;

import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class UserView {
    private String email;

    private String fullName;

    private String role;

}
