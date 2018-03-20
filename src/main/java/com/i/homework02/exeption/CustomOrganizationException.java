package com.i.homework02.exeption;

public class CustomOrganizationException extends RuntimeException {
    public CustomOrganizationException(String message) {
        super(message);
    }

    public CustomOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
