package com.i.homework02.exeption;

public class CustomUserException extends Exception {
    public CustomUserException(String message) {
        super(message);
    }

    public CustomUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
