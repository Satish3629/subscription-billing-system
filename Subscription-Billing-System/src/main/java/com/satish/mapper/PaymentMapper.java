package com.satish.mapper;

import com.satish.dto.PaymentDto;
import com.satish.entity.Payment;

public class PaymentMapper {
    public static Payment mapToPayment (PaymentDto paymentDto){
        return new Payment(
                paymentDto.getId(),
                paymentDto.getUserId(),
                paymentDto.getSubscriptionsId(),
                paymentDto.getAmount(),
                paymentDto.getTransactionId(),
                paymentDto.getPaymentDate(),
                paymentDto.getStatus()
        );
    }

    public static PaymentDto mapToPaymentDto (Payment payment){
        return new PaymentDto(
                payment.getId(),
                payment.getUser() != null ? payment.getUser().getId() : null,
                payment.getSubscriptions() != null ? payment.getSubscriptions().getId() : null,
                payment.getAmount(),
                payment.getTransactionId(),
                payment.getPaymentDate(),
                payment.getStatus()
        );
    }
}
