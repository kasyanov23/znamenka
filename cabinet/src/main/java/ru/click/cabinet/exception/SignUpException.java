package ru.click.cabinet.exception;

/**
 * Ошибка регистрации
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class SignUpException extends RuntimeException {
    /**
     * {@inheritDoc}
     */
    public SignUpException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public SignUpException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
