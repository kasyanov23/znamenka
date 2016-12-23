package ru.click.crm.service.subsystem.message;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.click.core.model.MessageFromClient;
import ru.click.core.model.User;
import ru.click.core.repository.domain.MessageFromClientRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.springframework.util.Assert.notNull;
import static ru.click.core.model.QMessageFromClient.messageFromClient;

/**
 * Сервис для получения сообщений от клиента
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class MessageFromClientService implements MessageService<MessageFromClient> {

    /**
     * Репозиторий сообщений
     */
    private final MessageFromClientRepository messageRepository;

    /**
     * Конструктор для внедрения зависимостей
     * @param messageRepository репозиторий
     */
    @Autowired
    public MessageFromClientService(MessageFromClientRepository messageRepository) {
        notNull(messageRepository, "MessageRepository must be not null");
        this.messageRepository = messageRepository;
    }

    /**
     * Получает список сообщений, отправленных данному пользователю
     * клиентом из личного кабинета
     * @param user пользователь, которому пришло сообщение
     * @return список сообщений
     */
    @Override
    public List<MessageFromClient> getMessage(User user) {
        Predicate predicate = messageFromClient.destinationUsername.eq(user.getUsername())
                .and(messageFromClient.studio.eq(1));
        Sort ascCreatedDate = new Sort(Sort.Direction.ASC, "createdDate");
        Iterable<MessageFromClient> messages = messageRepository.findAll(predicate, ascCreatedDate);
        return stream(messages.spliterator(), false).collect(toList());
    }
}
