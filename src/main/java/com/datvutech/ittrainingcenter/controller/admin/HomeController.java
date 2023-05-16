package com.datvutech.ittrainingcenter.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class HomeController {
    @GetMapping
    public String showAdminHomepage() {
       
        return "admin/homepage";
    }
}
