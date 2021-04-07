package com.arctest.calc.exception.handler;

import com.arctest.calc.exception.CalculationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class CalculationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { CalculationException.class, ArithmeticException.class })
    protected ResponseEntity<Object> handleConflict( RuntimeException ex, WebRequest request) {
        log.error("Exception occurred: {}, request: {}", ex, request);
        String bodyOfResponse = String.format("Exception occurred: %s", ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
