package com.orderdomaincore.exception;

import com.commondomain.exception.DomainException;
import lombok.Getter;

@Getter
public class OrderDomainException extends DomainException {
    private final String message;

    public OrderDomainException(String message) {
        super(message);
        this.message = message;
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
