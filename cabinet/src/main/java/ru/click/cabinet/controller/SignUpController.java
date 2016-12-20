package ru.click.cabinet.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import ru.click.cabinet.exception.NoExistsClientSignUpException;
import ru.click.cabinet.exception.SignUpException;
import ru.click.cabinet.service.SignUpService;
import ru.click.core.model.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.Assert.notNull;

@Controller
@RequestMapping("/sign-up")
@Slf4j
public class SignUpController {

    private final SignUpService service;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SignUpController(SignUpService service, AuthenticationManager authenticationManager) {
        notNull(service);
        this.service = service;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendCode(@RequestParam String phone, HttpSession session) {
        Client client = service.sendSms(phone);
        session.setAttribute("phone", phone);
        return ok(client.getName());
    }


    @PostMapping("/verify")
    public ResponseEntity verify(@RequestParam Integer code, HttpSession session) {
        String phone = (String) session.getAttribute("phone");
        service.verify(phone, code);
        return ok().build();
    }

    @PostMapping("/confirm")
    public ResponseEntity confirm(
            @RequestParam("p") String encodedPassword,
            @RequestParam("c") String encodedConfirmPassword,
            HttpSession session,
            HttpServletRequest request
    ) {

        String phone = (String) session.getAttribute("phone");
        byte[] decodedBytes = Base64Utils.decodeFromString(encodedPassword);
        String password = new String(decodedBytes, UTF_8);
        service.confirm(phone, password, encodedPassword.equals(encodedConfirmPassword));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phone, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        return ok().build();
    }


    @ExceptionHandler(SignUpException.class)
    @ResponseStatus(BAD_REQUEST)
    public void errorHandle(SignUpException e) {
        log.error(e.getMessage(), e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> validationErrorHandle(ConstraintViolationException e) {
        val messages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return badRequest().body(StringUtils.join(messages, ','));
    }

    @ExceptionHandler(NoExistsClientSignUpException.class)
    @ResponseStatus(NO_CONTENT)
    public void noExistsClientHandle(NoExistsClientSignUpException e) {
        log.error(e.getMessage(), e);
    }


}
