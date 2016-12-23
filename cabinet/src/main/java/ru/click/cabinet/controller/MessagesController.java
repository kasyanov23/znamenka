package ru.click.cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.click.cabinet.exception.message.MessageException;
import ru.click.cabinet.service.IMessageService;
import ru.click.core.model.Client;

import static com.mysema.commons.lang.Assert.notNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Контроллер для отправки сообщений персоналу студии
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@RestController
@RequestMapping("/message")
public class MessagesController {

    private final IMessageService messageService;

    @Autowired
    public MessagesController(IMessageService messageService) {
        notNull(messageService, "MessageService");
        this.messageService = messageService;
    }

    /**
     * Метод для отправки сообщения персоналу студии
     *
     * @param username имя пользователя crm, адресат
     * @param text     текст сообщения
     * @param client   клиент, отправитель
     */
    @PostMapping("/send")
    public ResponseEntity sendMessage(
            @RequestParam String username,
            @RequestParam String text,
            @RequestParam Integer studio,
            @ModelAttribute Client client
    ) {
        messageService.sendMessage(username, text, client, studio);
        return ok().build();
    }

    @ExceptionHandler(MessageException.class)
    @ResponseStatus(BAD_REQUEST)
    public void errorHandler(){}

}
