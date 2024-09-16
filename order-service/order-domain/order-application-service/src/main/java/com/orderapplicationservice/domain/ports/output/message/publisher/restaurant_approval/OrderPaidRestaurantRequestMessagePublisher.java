package com.orderapplicationservice.domain.ports.output.message.publisher.restaurant_approval;

import com.commondomain.event.publisher.DomainEventPublisher;
import com.orderdomaincore.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
