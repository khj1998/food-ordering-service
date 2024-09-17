package com.orderapplicationservice.domain;

import com.orderapplicationservice.domain.dto.create.CreateOrderCommand;
import com.orderapplicationservice.domain.dto.create.CreateOrderResponse;
import com.orderapplicationservice.domain.mapper.OrderDataMapper;
import com.orderapplicationservice.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.orderdomaincore.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);

        log.info("Order is created with id : {}",orderCreatedEvent.getOrder().getId());

        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);

        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }
}
