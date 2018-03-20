package com.i.homework02.exeption;

public class CustomOfficeException extends RuntimeException{
    public CustomOfficeException(String message) {
        super(message);
    }

    public CustomOfficeException(String message, Throwable cause) {
        super(message, cause);
    }
}
