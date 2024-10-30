package com.orderdomaincore.exception;

import com.commondomain.exception.DomainException;
import lombok.Getter;

@Getter
public class OrderNotFoundException extends DomainException {
    private final String message;

    public OrderNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
