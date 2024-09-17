package com.orderapplicationservice.order.service.domain;

import com.orderapplicationservice.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.orderapplicationservice.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.orderapplicationservice.domain.ports.output.message.publisher.restaurant_approval.OrderPaidRestaurantRequestMessagePublisher;
import com.orderapplicationservice.domain.ports.output.repository.CustomerRepository;
import com.orderapplicationservice.domain.ports.output.repository.OrderRepository;
import com.orderapplicationservice.domain.ports.output.repository.RestaurantRepository;
import com.orderdomaincore.service.OrderDomainService;
import com.orderdomaincore.service.OrderDomainServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.orderapplicationservice")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
