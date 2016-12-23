package ru.click.cabinet.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.click.cabinet.exception.message.DataAccessMessageException;
import ru.click.cabinet.exception.message.IllegalArgumentMessageException;
import ru.click.cabinet.exception.message.UnknownMessageException;
import ru.click.core.model.Client;
import ru.click.core.model.MessageFromClient;
import ru.click.core.repository.domain.MessageFromClientRepository;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

/**
 * Сервис для отправки сообщений персоналу студии
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class MessageService implements IMessageService {

    private final MessageFromClientRepository messageRepository;

    @Autowired
    public MessageService(MessageFromClientRepository messageRepository) {
        notNull(messageRepository, "Message Repository must not be null");
        this.messageRepository = messageRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(String destUser, String text, Client sender, Integer studio) {
        try {
        hasText(destUser, "Username must has text");
        hasText(text, "MessageText must has text");
        notNull(sender, "Client must not be null");
        notNull(studio, "Studio must not be null");
        val message = new MessageFromClient()
                .setClient(sender)
                .setStudio(studio)
                .setDestinationUsername(destUser)
                .setText(text);
            messageRepository.save(message);
        } catch (DataAccessException e) {
            throw new DataAccessMessageException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentMessageException(e.getMessage(), e);
        } catch (Throwable e) {
            throw new UnknownMessageException(e.getMessage(), e);
        }
    }

}
