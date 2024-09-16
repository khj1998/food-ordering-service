package com.orderapplicationservice.domain.ports.input.message.listener.payment;

import com.orderapplicationservice.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
