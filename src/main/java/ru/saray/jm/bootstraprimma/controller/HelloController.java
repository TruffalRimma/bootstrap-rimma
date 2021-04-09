package ru.saray.jm.bootstraprimma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    public String welcomePage() {
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
