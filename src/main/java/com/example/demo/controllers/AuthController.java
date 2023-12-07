package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AuthController {
    @GetMapping
    public String index(){
        return "index";
    }
}
