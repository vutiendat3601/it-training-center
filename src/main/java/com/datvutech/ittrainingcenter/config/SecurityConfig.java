package com.datvutech.ittrainingcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .formLogin(form -> form.loginPage("/login")
                        /* .loginProcessingUrl("/login") */
                        .defaultSuccessUrl("/register"))
                .authorizeHttpRequests(reqs -> reqs
                        .antMatchers("/sb-admin-2/**", "/login", "/verify", "/register", "/favicon.ico", "/img/**")
                        .permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
