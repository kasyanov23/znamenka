package ru.click.cabinet.exception.signup;

/**
 * Ошибка регистрации, возникающаяся при несовпадении кода,
 * отправленному клиенту по смс, и кодом, который ввел пользователей
 * <p>
 * Создан 19.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class WrongCodeSignUpException extends SignUpException {
    /**
     * {@inheritDoc}
     */
    public WrongCodeSignUpException() {
    }

    /**
     * {@inheritDoc}
     */
    public WrongCodeSignUpException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public WrongCodeSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
