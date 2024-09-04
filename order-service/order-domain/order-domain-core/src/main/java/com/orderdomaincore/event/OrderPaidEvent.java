package com.orderdomaincore.event;

import com.orderdomaincore.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, LocalDateTime createdAt) {
        super(order, createdAt);
    }
}
