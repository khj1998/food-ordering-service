package com.orderapplication.exception;

import com.commondomain.exception.ErrorDto;
import com.commondomain.exception.GlobalExceptionHandler;
import com.orderdomaincore.exception.OrderDomainException;
import com.orderdomaincore.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler extends GlobalExceptionHandler {
    @ExceptionHandler(value = {OrderDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(OrderDomainException orderDomainException) {
        log.error(orderDomainException.getMessage(),orderDomainException);
        return ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(orderDomainException.getMessage())
                .build();
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(OrderNotFoundException orderNotFoundException) {
        log.error(orderNotFoundException.getMessage(),orderNotFoundException);
        return ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(orderNotFoundException.getMessage())
                .build();
    }
}
