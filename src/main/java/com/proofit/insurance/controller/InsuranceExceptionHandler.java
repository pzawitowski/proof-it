package com.proofit.insurance.controller;

import com.proofit.insurance.view.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InsuranceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> handleException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(exception.getMessage()));
    }
}
