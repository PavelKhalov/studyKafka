package ru.khalov.tests.depositmicroservice.error;

public class RetryableError extends RuntimeException{
    public RetryableError(String message) {
        super(message);
    }

    public RetryableError(String message, Throwable cause) {
        super(message, cause);
    }
}
