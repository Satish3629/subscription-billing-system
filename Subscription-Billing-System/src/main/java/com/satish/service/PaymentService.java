package com.satish.service;

import com.satish.dto.PaymentDto;
import com.satish.entity.Payment;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);

    Page<Payment> getAllPayments(int page, int size, String status);

    PaymentDto getPaymentsById(Long id);

    List<PaymentDto> getPaymentsByUserId(Long userId);

}
