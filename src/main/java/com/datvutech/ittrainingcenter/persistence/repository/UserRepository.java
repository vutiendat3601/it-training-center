package com.datvutech.ittrainingcenter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datvutech.ittrainingcenter.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailVerificationCode(String code);
}
