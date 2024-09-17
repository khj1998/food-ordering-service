package com.orderdomaincore.service;

import com.orderdomaincore.entity.Order;
import com.orderdomaincore.entity.Product;
import com.orderdomaincore.entity.Restaurant;
import com.orderdomaincore.event.OrderCancelledEvent;
import com.orderdomaincore.event.OrderCreatedEvent;
import com.orderdomaincore.event.OrderPaidEvent;
import com.orderdomaincore.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order,restaurant);
        order.validateOrder();
        order.initializeOrder();

        log.info("Order with id : {} is initiated",order.getId().getValue());
        return new OrderCreatedEvent(order, LocalDateTime.now());
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();

        log.info("Order with id : {} is paid",order.getId().getValue());
        return new OrderPaidEvent(order,LocalDateTime.now());
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id : {} is approved",order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id : {}",order.getId().getValue());

        return new OrderCancelledEvent(order,LocalDateTime.now());
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id : {} is cancelled",order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id : "+restaurant.getId().getValue() +
                    " is currently not active!");
        }
    }

    /**
     * HashSet으로 연산량 개선 가능.
     */
    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getOrderItemList().forEach(orderItem -> restaurant.getProductList().forEach(restaurantProduct -> {
            Product currentProduct = orderItem.getProduct();

            if (currentProduct.equals(restaurantProduct)) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                        restaurantProduct.getPrice());
            }
        }));
    }
}
