package com.abnamro.api;

import com.abnamro.api.response.ErrorResponse;
import com.abnamro.utils.validator.ValidationException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {

        val errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}