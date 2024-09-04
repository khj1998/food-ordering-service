package com.orderdomaincore.service;

import com.orderdomaincore.entity.Order;
import com.orderdomaincore.entity.Restaurant;
import com.orderdomaincore.event.OrderCancelledEvent;
import com.orderdomaincore.event.OrderCreatedEvent;
import com.orderdomaincore.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order,List<String> failureMessages);
}
