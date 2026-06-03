package ru.khalov.tests.withdrawmicriservice.error;

public class NonRetryableError extends RuntimeException{

    public NonRetryableError(String message) {
        super(message);
    }

    public NonRetryableError(String message, Throwable cause) {
        super(message, cause);
    }
}
