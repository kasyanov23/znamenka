package ru.click.cabinet.exception.signup;

/**
 * Ошибка регистрации, возникающая во всяких непредвиденных ситуациях
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class UnknownSignUpException extends SignUpException {
    /**
     * {@inheritDoc}
     */
    public UnknownSignUpException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public UnknownSignUpException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public UnknownSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
