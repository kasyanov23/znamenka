package ru.click.crm.service.subsystem.message;

import ru.click.core.model.User;

import java.util.List;

/**
 * Интерфейс для получения сообщений пользователем
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@FunctionalInterface
public interface MessageService<M> {
    /**
     * Получить сообщение типа T для пользователя CRM
     *
     * @param user пользователь, которому пришло сообщение
     * @return сообщение
     */
    List<M> getMessage(User user);
}
