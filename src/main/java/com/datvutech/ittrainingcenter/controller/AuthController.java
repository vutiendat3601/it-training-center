package com.datvutech.ittrainingcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datvutech.ittrainingcenter.controller.model.request.LearnerLoginReq;
import com.datvutech.ittrainingcenter.service.AuthService;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginLearner(Model model) {
        model.addAttribute("learnerLogin", new LearnerLoginReq());
        return "auth/login";
    }

    /* @PostMapping("/login")
    public String loginLearner(LearnerLoginReq learnerLoginReq) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                learnerLoginReq.getEmail(), learnerLoginReq.getPassword());
        authService.authenticate(authentication);
        return "redirect:auth/register";
    }  */

    @GetMapping("/verify")
    public String verifyEmailAddress(@RequestParam String code, Model model) {
        authService.verifyEmailAddress(code);
        model.addAttribute("message", "Địa chỉ email đã được xác thực thành công!");
        return "success";
    }
}
