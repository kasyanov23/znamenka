package ru.click.cabinet.exception;

public class SignUpException extends RuntimeException {

    public SignUpException() {
        super();
    }

    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
