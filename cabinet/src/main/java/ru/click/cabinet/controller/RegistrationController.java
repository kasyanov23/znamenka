package ru.click.cabinet.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Сережа on 19.12.2016.
 */
public class RegistrationController {

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
