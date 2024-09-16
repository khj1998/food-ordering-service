package com.orderapplicationservice.domain.ports.output.message.publisher.payment;

import com.commondomain.event.publisher.DomainEventPublisher;
import com.orderdomaincore.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
