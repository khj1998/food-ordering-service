package com.orderapplicationservice.domain.mapper;

import com.commondomain.vo.Money;
import com.commondomain.vo.ProductId;
import com.commondomain.vo.RestaurantId;
import com.orderapplicationservice.domain.dto.create.CreateOrderCommand;
import com.orderapplicationservice.domain.dto.create.CreateOrderResponse;
import com.orderapplicationservice.domain.dto.create.OrderAddress;
import com.orderapplicationservice.domain.dto.create.CreatedOrderItem;
import com.orderdomaincore.entity.Order;
import com.orderdomaincore.entity.OrderItem;
import com.orderdomaincore.entity.Product;
import com.orderdomaincore.entity.Restaurant;
import com.orderdomaincore.vo.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .restaurantId(createOrderCommand.getRestaurantId())
                .productList(createOrderCommand.getCreatedOrderItemList()
                        .stream().map(createdOrderItem -> new Product(new ProductId(createdOrderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(createOrderCommand.getCustomerId())
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .streetAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .itemList(orderItemsToOrderItemEntities(createOrderCommand.getCreatedOrderItemList()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<CreatedOrderItem> createdOrderItemList) {
        return createdOrderItemList.stream()
                .map(orderItem ->
                        OrderItem.Builder.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build())
                .collect(Collectors.toList());
    }
}
