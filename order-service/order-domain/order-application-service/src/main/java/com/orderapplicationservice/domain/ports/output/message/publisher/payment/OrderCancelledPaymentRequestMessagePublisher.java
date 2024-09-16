package com.orderapplicationservice.domain.ports.output.message.publisher.payment;

import com.commondomain.event.DomainEvent;
import com.orderdomaincore.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEvent<OrderCancelledEvent> {
}
