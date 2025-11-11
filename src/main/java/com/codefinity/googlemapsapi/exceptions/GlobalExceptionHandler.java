package com.codefinity.googlemapsapi.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ExternalServiceException.class, PhotoServiceException.class})
    public ResponseEntity<Error> handleExternalServiceException(ExternalServiceException ex) {
        return new ResponseEntity<>(Error.builder().errorMessage(ex.getMessage())
                .build(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<>(
                Error.builder()
                        .errorMessage(fieldErrors.stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .findAny().orElse("Invalid parameters"))
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

}
