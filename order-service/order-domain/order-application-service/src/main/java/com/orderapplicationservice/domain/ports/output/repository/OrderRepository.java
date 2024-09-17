package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Order;
import com.orderdomaincore.vo.TrackingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("select o from Order o where o.trackingId = :trackingId")
    Optional<Order> findByTrackingId(@Param("trackingId") TrackingId trackingId);
}
