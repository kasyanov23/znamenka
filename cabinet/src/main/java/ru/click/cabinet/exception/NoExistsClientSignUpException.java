package ru.click.cabinet.exception;

public class NoExistsClientSignUpException extends SignUpException {

    public NoExistsClientSignUpException() {
    }

    public NoExistsClientSignUpException(String message) {
        super(message);
    }

    public NoExistsClientSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
