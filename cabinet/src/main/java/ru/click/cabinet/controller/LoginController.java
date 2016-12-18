package ru.click.cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import ru.click.core.model.CalendarEvent;

import static org.springframework.util.Assert.notNull;

/**
 * Created by Сережа on 19.12.2016.
 */
public class LoginController {



    @GetMapping("/login")
    public String login() {
        return "/login";
    }


}
