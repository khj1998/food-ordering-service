package com.orderapplicationservice.domain.dto.message;

import com.commondomain.vo.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String restaurantId;
    private LocalDateTime createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}
