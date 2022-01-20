package com.example.spring_eshop.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(Exception exception, Model model){
        String errorMessage = (exception != null ? exception.getMessage() : "error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
