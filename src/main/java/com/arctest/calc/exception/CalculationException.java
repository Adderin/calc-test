package com.arctest.calc.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CalculationException extends RuntimeException{
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
        log.trace("Exception happened: {}, {}", message, cause);
    }

    public CalculationException(String message) {
        super(message);
    }
}
