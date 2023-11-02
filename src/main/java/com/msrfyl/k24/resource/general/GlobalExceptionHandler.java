package com.msrfyl.k24.resource.general;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HandleException.class)
    public ResponseEntity<Object> validationError(HandleException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMap(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

}

abstract class HandledExceptionValidation {

    HandledExceptionValidation(String subject, String value, String message, Boolean valid) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        body.put("subject", subject);
        body.put("value", value);

        if (!valid) {
            HandleException ex = new HandleException(message);
            ex.setMap(body);
            throw ex;
        }

    }

}
