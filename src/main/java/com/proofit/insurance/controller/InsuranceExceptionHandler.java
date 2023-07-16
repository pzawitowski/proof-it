package com.proofit.insurance.controller;

import com.proofit.insurance.view.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class InsuranceExceptionHandler extends ResponseEntityExceptionHandler {
    public static final Logger logger = Logger.getLogger(InsuranceExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception exception) {
        logger.log(Level.SEVERE, "An error occurred processing the entity", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong"));
    }
}
