package com.datvutech.ittrainingcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.datvutech.ittrainingcenter.security.AppAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .formLogin(form -> form.loginPage("/login")
                        .failureUrl("/login?error")
                        .successHandler(appAuthenticationSuccessHandler())
                        .permitAll())
                .authorizeHttpRequests(reqs -> reqs
                        /*
                         * .antMatchers("/sb-admin-2/**", "/verify", "/register", "/favicon.ico",
                         * "/img/**")
                         * .permitAll()
                         */
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/lecturer/**").hasRole("LECTURER")
                        .antMatchers("/learner/**").hasRole("LEARNER")
                        .anyRequest().permitAll());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationSuccessHandler appAuthenticationSuccessHandler() {
        return new AppAuthenticationSuccessHandler();
    }
}
