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
import ru.click.cabinet.exception.signup.NoExistsClientSignUpException;
import ru.click.cabinet.exception.signup.SignUpException;
import ru.click.cabinet.service.ISignUpService;
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

/**
 * Контроллер для регистрации клиента в нашей системе
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Controller
@RequestMapping("/sign-up")
@Slf4j
public class SignUpController {

    /**
     * Сервис для процесса регистрации
     */
    private final ISignUpService service;

    /**
     * Менеджер аунтефикации, необходим для автологина
     * после успешной регистрации клиента в системе
     */
    private final AuthenticationManager authManager;

    /**
     * Конструктор для внедрения зависимостей
     *
     * @param service     {@link this#service}
     * @param authManager {@link this#authManager}
     */
    @Autowired
    public SignUpController(ISignUpService service, AuthenticationManager authManager) {
        notNull(service);
        this.service = service;
        this.authManager = authManager;
    }

    /**
     * Отдает страницу регистрации
     *
     * @return название страницы
     */
    @GetMapping
    public String registration() {
        return "registration";
    }

    /**
     * Метод для отправки сгенерированного кода по смс клиенту.
     * Необходимо, чтобы в системе могли зарегистрироваться только клиенты студии
     *
     * @param phone   номер телефона в формате 10 цифр (без международного кода)
     * @param session сессия пользователя
     * @return статус 200, в тело метода записано имя клиента для отображения на фронте
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendCode(@RequestParam String phone, HttpSession session) {
        Client client = service.sendSms(phone);
        session.setAttribute("phone", phone);
        return ok(client.getName());
    }

    /**
     * Метод для повторной отправки смс клиенту
     *
     * @param session сессия пользователя
     * @return статус 200
     */
    // TODO: 20.12.2016 как то ограничить, чтобы никто не мог послать сотню запросов и увеличить издержки на смс
    @PostMapping("/sendAgain")
    public ResponseEntity sendCodeAgain(HttpSession session) {
        String phone = (String) session.getAttribute("phone");
        service.sendSmsAgain(phone);
        return ok().build();
    }

    /**
     * Метод для проверки введенего кода на соответствие отправленному
     *
     * @param code    код, который вбивается юзером
     * @param session сессия пользователя
     * @return статус 200
     */
    @PostMapping("/verify")
    public ResponseEntity verify(@RequestParam Integer code, HttpSession session) {
        String phone = (String) session.getAttribute("phone");
        service.verify(phone, code);
        return ok().build();
    }

    /**
     * Последний этап регистрации, закрепление пароля за пользователем
     *
     * @param encodedPassword        пароль в формате Base64
     * @param encodedConfirmPassword подтвержденный пароль в формате Base64
     * @param session                сессия пользователя
     * @param request                http запрос пользователя (нужен для автологина)
     * @return статус 200
     */
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
        Authentication authenticatedUser = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        return ok().build();
    }

    /**
     * Обработчик ошибок регистрации
     *
     * @param e ошибка регистрации клиенте
     */
    @ExceptionHandler(SignUpException.class)
    @ResponseStatus(BAD_REQUEST)
    public void errorHandle(SignUpException e) {
        log.error(e.getMessage(), e);
    }

    /**
     * Обработчик ошибок валидации
     *
     * @param e ошибка валидации
     * @return статус 400 (bad request), в тело записаны сообщения об ошибках валидации через запятую
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> validationErrorHandle(ConstraintViolationException e) {
        val messages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return badRequest().body(StringUtils.join(messages, ','));
    }

    /**
     * Обработчик ошибки, возникающей в случае, если такого номера телефона в базу у нас нет
     *
     * @param e ошибка несуществующего клиента
     */
    @ExceptionHandler(NoExistsClientSignUpException.class)
    @ResponseStatus(NO_CONTENT)
    public void noExistsClientHandle(NoExistsClientSignUpException e) {
        log.error(e.getMessage(), e);
    }


}
