package com.i.homework02.exeption;

public class CustomAccountException extends Exception{
    public CustomAccountException(String message) {
        super(message);
    }

    public CustomAccountException(String message, Throwable cause) {
        super(message, cause);
    }


}
