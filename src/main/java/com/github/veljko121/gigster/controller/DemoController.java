package com.github.veljko121.gigster.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String hello() {
        return "Hello!";
    }

}
