package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
