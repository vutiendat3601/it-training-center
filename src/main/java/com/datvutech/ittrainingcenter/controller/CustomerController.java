package com.datvutech.ittrainingcenter.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.datvutech.ittrainingcenter.controller.model.request.LearnerRegisterReq;
import com.datvutech.ittrainingcenter.persistence.entity.User;
import com.datvutech.ittrainingcenter.service.UserService;
import com.datvutech.ittrainingcenter.validation.annotation.ConfirmPassword;

@Controller
public class CustomerController {
    private ModelMapper mapper = new ModelMapper();
    private UserService userService;
    private PasswordEncoder passEncoder;

    public CustomerController(UserService userService, PasswordEncoder passEncoder) {
        this.userService = userService;
        this.passEncoder = passEncoder;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("learnerRegister", new LearnerRegisterReq());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerLearner(
            @Valid @ModelAttribute("learnerRegister") LearnerRegisterReq learnerRegister,
            BindingResult validResult, Model model) {

        if (validResult.hasErrors()) {
            Optional<ObjectError> confirmPasswordError = validResult.getAllErrors().stream()
                    .filter(e -> e.getCode().equals(ConfirmPassword.class.getSimpleName()))
                    .findFirst();
            if (confirmPasswordError.isPresent()) {
                model.addAttribute("confirmPasswordError", confirmPasswordError.get());
            }
            return "auth/register";
        }
        User userReq = mapper.map(learnerRegister, User.class);
        userReq.setPwd(passEncoder.encode(learnerRegister.getPassword()));
        /* model.addAttribute("learnerRegister", new LearnerRegisterReq()); */
        User userResp = userService.registerLearner(userReq);
        model.addAttribute("message", """
                Tài khoản %s đã được đăng ký thành công!
                Vui lòng kiểm tra hộp thư mail xác thực địa chỉ email.
                """.formatted(userResp.getEmail()));
        return "success";
    }
}
