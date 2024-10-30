package com.commondomain.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        log.error(exception.getMessage(),exception);
        return ErrorDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(ValidationException validationException) {
        ErrorDto errorDto;

        if (validationException instanceof ConstraintViolationException) {
            String violationMessages = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violationMessages,validationException);
            errorDto = ErrorDto.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violationMessages)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage,validationException);
            errorDto = ErrorDto.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }
        return errorDto;
    }

    private String extractViolationsFromException(ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
