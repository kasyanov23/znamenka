package ru.click.cabinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.click.cabinet.service.SignUpService;

import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/sign-up")
public class UserController {

    private final SignUpService service;

    public UserController(SignUpService service) {
        notNull(service);
        this.service = service;
    }

    @PostMapping
    public String sendCode(String phone) {
        service.sendSms(phone);
        return "redirect:/";
    }
}
