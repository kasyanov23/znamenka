package ru.click.cabinet.exception;

public class WrongCodeSignUpException extends SignUpException {

    public WrongCodeSignUpException() {
    }

    public WrongCodeSignUpException(String message) {
        super(message);
    }

    public WrongCodeSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
