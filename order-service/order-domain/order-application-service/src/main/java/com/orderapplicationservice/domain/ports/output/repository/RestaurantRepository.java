package com.orderapplicationservice.domain.ports.output.repository;

import com.orderdomaincore.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
