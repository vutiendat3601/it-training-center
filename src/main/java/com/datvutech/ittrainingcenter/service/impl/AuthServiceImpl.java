package com.datvutech.ittrainingcenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datvutech.ittrainingcenter.exception.ExceptionMessages;
import com.datvutech.ittrainingcenter.persistence.entity.User;
import com.datvutech.ittrainingcenter.persistence.repository.UserRepository;
import com.datvutech.ittrainingcenter.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepo;
    private ExceptionMessages exMessages;
    private PasswordEncoder passEncoder;

    public AuthServiceImpl(UserRepository userRepo, ExceptionMessages exMessages,
            PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.exMessages = exMessages;
        this.passEncoder = passEncoder;
    }

    @Override
    public User verifyEmailAddress(String code) {
        User user = userRepo.findByEmailVerificationCode(code)
                .orElseThrow(() -> new RuntimeException(exMessages.getMessage("EMAIL_VERIFICATION_CODE_NOT_FOUND")));
        user.setEmailVerified(true);
        userRepo.save(user);
        return user;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passEncoder.matches(password, user.getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            }
            throw new BadCredentialsException("Invalid password!");
        }
        throw new BadCredentialsException("No user registered with this details!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}
