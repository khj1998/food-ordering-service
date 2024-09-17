package com.orderapplicationservice.domain;

import com.orderapplicationservice.domain.dto.track.TrackOrderQuery;
import com.orderapplicationservice.domain.dto.track.TrackOrderResponse;
import com.orderapplicationservice.domain.mapper.OrderDataMapper;
import com.orderapplicationservice.domain.ports.output.repository.OrderRepository;
import com.orderdomaincore.entity.Order;
import com.orderdomaincore.exception.OrderNotFoundException;
import com.orderdomaincore.vo.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackId()));

        if (orderResult.isEmpty()) {
            log.info("could not find order from given tracking id : {}",trackOrderQuery.getOrderTrackId());
            throw new OrderNotFoundException("could not find order from given tracking id : "+trackOrderQuery.getOrderTrackId());
        }

        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
