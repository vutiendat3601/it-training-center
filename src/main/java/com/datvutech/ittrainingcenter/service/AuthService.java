package com.datvutech.ittrainingcenter.service;

import org.springframework.security.authentication.AuthenticationProvider;

import com.datvutech.ittrainingcenter.persistence.entity.User;

public interface AuthService extends AuthenticationProvider {
    User verifyEmailAddress(String code);
}
