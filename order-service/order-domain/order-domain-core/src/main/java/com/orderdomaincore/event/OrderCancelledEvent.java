package com.orderdomaincore.event;

import com.orderdomaincore.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, LocalDateTime createdAt) {
        super(order, createdAt);
    }
}
