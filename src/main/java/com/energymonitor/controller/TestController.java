package com.energymonitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/public")
    public String publicAccess() {
        return "Public Content.";
    }

    @GetMapping("/private")
    public String privateAccess() {
        return "Private Content.";
    }
}
