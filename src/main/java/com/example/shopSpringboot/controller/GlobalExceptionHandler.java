package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.confi.EmailSendingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailSendingException.class)
    public ModelAndView handleEmailSendingException(EmailSendingException e){
        ModelAndView modelAndView = new ModelAndView("forgot_password");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }
}