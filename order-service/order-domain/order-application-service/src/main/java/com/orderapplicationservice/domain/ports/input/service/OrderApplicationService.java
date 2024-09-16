package com.orderapplicationservice.domain.ports.input.service;

import com.orderapplicationservice.domain.dto.create.CreateOrderCommand;
import com.orderapplicationservice.domain.dto.create.CreateOrderResponse;
import com.orderapplicationservice.domain.dto.track.TrackOrderQuery;
import com.orderapplicationservice.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
