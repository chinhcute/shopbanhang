package com.example.shopSpringboot.confi;

public class EmailSendingException extends  RuntimeException{
    public EmailSendingException(String message) {
        super(message);
    }

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
