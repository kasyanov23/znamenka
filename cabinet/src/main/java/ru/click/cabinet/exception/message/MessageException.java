package ru.click.cabinet.exception.message;

import org.springframework.core.NestedRuntimeException;

/**
 * Рутовая ошибка для сервис отправки сообщений клиентом
 * персоналу студии
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class MessageException extends NestedRuntimeException {

    public MessageException(String msg) {
        super(msg);
    }

    public MessageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
