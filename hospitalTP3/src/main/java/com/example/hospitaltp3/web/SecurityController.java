package com.example.hospitaltp3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "NOT Authorized";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
