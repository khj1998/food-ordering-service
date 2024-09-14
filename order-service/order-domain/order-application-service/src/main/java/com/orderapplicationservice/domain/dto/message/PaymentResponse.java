package com.orderapplicationservice.domain.dto.message;

import com.commondomain.vo.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String paymentId;
    private String customerId;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}
