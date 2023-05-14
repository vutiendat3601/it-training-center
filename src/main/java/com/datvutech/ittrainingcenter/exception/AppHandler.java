package com.datvutech.ittrainingcenter.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
