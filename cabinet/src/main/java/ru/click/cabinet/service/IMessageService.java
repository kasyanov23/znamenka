package ru.click.cabinet.service;

import ru.click.core.model.Client;

/**
 * Интрефейс сервиса для отправки сообщений клиентом
 * персоналу студии
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@FunctionalInterface
public interface IMessageService {

    /**
     * Метод отправляющий сообщение
     * @param destUser юзернейм адресата
     * @param text текст сообщений
     * @param sender отправитель
     * @param studio код студии
     */
    void sendMessage(String destUser, String text, Client sender, Integer studio);
}
