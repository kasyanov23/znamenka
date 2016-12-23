package ru.click.cabinet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.click.core.model.Client;
import ru.click.core.model.LkUser;

/**
 * Содержит общий для всех контроллеров функционал
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@ControllerAdvice
public class Advice {

    /**
     * Вытаскивает клиента из аунтификации юзера
     *
     * @param auth аунтификация
     * @return клиент студии
     */
    @ModelAttribute
    public Client extractClient(Authentication auth) {
        LkUser user = (LkUser) auth.getPrincipal();
        return user.getClient();
    }
}
