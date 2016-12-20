package ru.click.cabinet.exception;

/**
 * Ошибка регистрации, возникающее если такого клиента не существует в базе
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class NoExistsClientSignUpException extends SignUpException {

    /**
     * {@inheritDoc}
     */
    public NoExistsClientSignUpException() {
    }

    /**
     * {@inheritDoc}
     */
    public NoExistsClientSignUpException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoExistsClientSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
