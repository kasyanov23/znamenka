package ru.click.cabinet.exception.message;

/**
 * Обертка для не прогнозируемых исключений
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class UnknownMessageException extends MessageException {
    public UnknownMessageException(String msg) {
        super(msg);
    }

    public UnknownMessageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
