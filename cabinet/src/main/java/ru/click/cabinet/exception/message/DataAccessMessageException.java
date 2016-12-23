package ru.click.cabinet.exception.message;

/**
 * Исключение, возникающее при неудачном сохранении
 * сообщения в базу
 * <p>
 * Создан 23.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class DataAccessMessageException extends MessageException {

    public DataAccessMessageException(String message) {
        super(message);
    }

    public DataAccessMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
