package com.i.homework02.exeption;

public class CustomAccountException extends RuntimeException{
    public CustomAccountException(String message) {
        super(message);
    }

    public CustomAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
