package ru.click.cabinet.exception;

/**
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class UnknownSignUpException extends SignUpException {

    public UnknownSignUpException() {
        super();
    }

    public UnknownSignUpException(String message) {
        super(message);
    }

    public UnknownSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
