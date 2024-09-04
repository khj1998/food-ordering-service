package com.orderdomaincore.event;

import com.commondomain.event.DomainEvent;
import com.orderdomaincore.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final LocalDateTime createdAt;

    public OrderEvent(Order order, LocalDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }
}
