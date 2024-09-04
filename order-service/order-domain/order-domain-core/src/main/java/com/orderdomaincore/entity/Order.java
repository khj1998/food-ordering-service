package com.orderdomaincore.entity;

import com.commondomain.entity.AggregateRoot;
import com.commondomain.vo.*;
import com.orderdomaincore.exception.OrderDomainException;
import com.orderdomaincore.vo.OrderItemId;
import com.orderdomaincore.vo.StreetAddress;
import com.orderdomaincore.vo.TrackingId;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress streetAddress;
    private final Money price; // 주믄 금액 총액
    private final List<OrderItem> orderItemList; // 주문 상품 리스트

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;

        for (OrderItem orderItem : orderItemList) {
            orderItem.initializeOrderItem(super.getId(),new OrderItemId(itemId++));
        }
    }
    
    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateItemsPrice() {
        Money orderItemPriceTotal = orderItemList.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO,Money::add);

        if (!price.equals(orderItemPriceTotal)) {
            throw new OrderDomainException("Total price : "+price.getAmount()+" is not equal as Order items total : "+orderItemPriceTotal.getAmount());
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: "+orderItem.getPrice().getAmount() +
                    "is not valid for product "+orderItem.getProduct().getId().getValue());
        }
    }

    public void pay() {
        if (!orderStatus.equals(OrderStatus.PENDING)) {
            throw new OrderDomainException("Order is not in correct state for paying");
        }

        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (!orderStatus.equals(OrderStatus.PAID)) {
            throw new OrderDomainException("Order is not in correct state for approving operation!");
        }

        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for initCancel operation!");
        }

        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.CANCELLING) {
            throw new OrderDomainException("Order is not in correct state for canceling operation!");
        }

        orderStatus = OrderStatus.CANCELED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().
                    filter(message -> !message.isEmpty()).toList());
        }

        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        streetAddress = builder.streetAddress;
        price = builder.price;
        orderItemList = builder.itemList;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress streetAddress;
        private Money price;
        private List<OrderItem> itemList;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder streetAddress(StreetAddress val) {
            streetAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder itemList(List<OrderItem> val) {
            itemList = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
