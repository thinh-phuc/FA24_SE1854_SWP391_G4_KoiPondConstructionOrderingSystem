package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidation(MethodArgumentNotValidException exception) {
        String message = "";
        //cu moi thuoc tinh loi thi xu ly gan vao bien message
        for (FieldError fielderror : exception.getBindingResult().getFieldErrors()) {
            message += fielderror.getField() + ": " + fielderror.getDefaultMessage();
        }
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleValidation(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
