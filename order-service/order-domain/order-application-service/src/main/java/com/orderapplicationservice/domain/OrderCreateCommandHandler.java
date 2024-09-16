package com.orderapplicationservice.domain;

import com.orderapplicationservice.domain.dto.create.CreateOrderCommand;
import com.orderapplicationservice.domain.dto.create.CreateOrderResponse;
import com.orderapplicationservice.domain.mapper.OrderDataMapper;
import com.orderapplicationservice.domain.ports.output.repository.CustomerRepository;
import com.orderapplicationservice.domain.ports.output.repository.OrderRepository;
import com.orderapplicationservice.domain.ports.output.repository.RestaurantRepository;
import com.orderdomaincore.entity.Customer;
import com.orderdomaincore.entity.Order;
import com.orderdomaincore.entity.Restaurant;
import com.orderdomaincore.event.OrderCreatedEvent;
import com.orderdomaincore.exception.OrderDomainException;
import com.orderdomaincore.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;;

    private final RestaurantRepository restaurantRepository;

    private final OrderDataMapper orderDataMapper;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);

        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order,restaurant);
        Order orderResult = saveOrder(order);

        log.info("Order is created with id : {}",orderResult.getId().getValue());

        return orderDataMapper.orderToCreateOrderResponse(orderResult);
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);

        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id : {}",createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id : " + createOrderCommand.getRestaurantId());
        }

        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);

        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id : {}",customerId);
            throw new OrderDomainException("Could not find customer with customer id : "+customerId);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);

        if (orderResult == null) {
            log.error("OrderResult must not be null!!");
            throw new OrderDomainException("Could not save order!");
        }

        log.info("Order is saved with id : {}",orderResult.getId());

        return order;
    }
}
