package ru.click.cabinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/user")
    public String s() {
        return "step2";
    }

}
