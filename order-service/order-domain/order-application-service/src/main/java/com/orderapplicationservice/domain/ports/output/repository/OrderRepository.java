package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Order;
import com.orderdomaincore.vo.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
