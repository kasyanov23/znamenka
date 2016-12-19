package ru.click.cabinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Сережа on 19.12.2016.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }


}
