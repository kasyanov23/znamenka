package ru.click.cabinet.exception.message;

/**
 * Исключение возникающее, когда в метод передается неверный параметр
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class IllegalArgumentMessageException extends MessageException {

    public IllegalArgumentMessageException(String s) {
        super(s);
    }

    public IllegalArgumentMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
