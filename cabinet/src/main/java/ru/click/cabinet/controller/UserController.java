package ru.click.cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.click.cabinet.service.SignUpService;

import javax.servlet.http.HttpSession;

import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/sign-up")
public class UserController {

    private final SignUpService service;

    @Autowired
    public UserController(SignUpService service) {
        notNull(service);
        this.service = service;
    }
    @GetMapping
    public String signUpStep1() {
        return "step1";
    }

    @PostMapping
    public String sendCode(String phone, HttpSession session) {
        service.sendSms(phone);
        session.setAttribute("phone", phone);
        return "redirect:/sign-up/confirm";
    }

    @GetMapping("/confirm")
    public String signUpStep2(HttpSession session) {
        return "step2";
    }

    @PostMapping("/confirm")
    public String confirm(Integer code, HttpSession session) {
        String phone = (String) session.getAttribute("phone");
        String password = service.signUp(phone, code);
        session.setAttribute("password", password);
        return "redirect:/sign-up/confirm";
    }


}
