package com.orderapplication.controller;

import com.orderapplicationservice.domain.dto.create.CreateOrderCommand;
import com.orderapplicationservice.domain.dto.create.CreateOrderResponse;
import com.orderapplicationservice.domain.dto.track.TrackOrderQuery;
import com.orderapplicationservice.domain.dto.track.TrackOrderResponse;
import com.orderapplicationservice.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @GetMapping
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@RequestParam("trackingId")UUID trackingId) {
        TrackOrderResponse trackOrderResponse =
                orderApplicationService.trackOrder(TrackOrderQuery.builder().orderTrackId(trackingId).build());
        log.info("Returning order status with tracking id : {}",trackOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(trackOrderResponse);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Create order request received: {}", createOrderCommand);
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id : {}",createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }
}
