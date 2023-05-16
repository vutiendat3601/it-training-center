package com.datvutech.ittrainingcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datvutech.ittrainingcenter.service.AuthService;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/verify")
    public String verifyEmailAddress(@RequestParam String code, Model model) {
        authService.verifyEmailAddress(code);
        model.addAttribute("message", "Địa chỉ email đã được xác thực thành công!");
        return "success";
    }
}
