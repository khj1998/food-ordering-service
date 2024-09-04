package com.orderdomaincore.event;

import com.orderdomaincore.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCreatedEvent extends OrderEvent {
    public OrderCreatedEvent(Order order, LocalDateTime createdAt) {
        super(order, createdAt);
    }
}
