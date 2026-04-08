package com.satish.service.impl;

import com.satish.dto.PaymentDto;
import com.satish.entity.Payment;
import com.satish.entity.Subscriptions;
import com.satish.entity.User;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.PaymentMapper;
import com.satish.repository.PaymentRepository;
import com.satish.repository.SubscriptionsRepository;
import com.satish.repository.UserRepository;
import com.satish.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionsRepository subscriptionsRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {

        Payment payment = PaymentMapper.mapToPayment(paymentDto);
        User user = userRepository.findById(paymentDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        Subscriptions subscriptions=subscriptionsRepository.findById(paymentDto.getSubscriptionsId())
                .orElseThrow(()->new ResourceNotFoundException("Plan not found..!!"));

        payment.setUser(user);
        payment.setSubscriptions(subscriptions);

        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.mapToPaymentDto(savedPayment);
    }

    @Override
    public Page<Payment> getAllPayments(int page, int size, String status) {
        Pageable pageable= PageRequest.of(page, size);
        return paymentRepository.findByStatus(status, pageable);
    }

    @Override
    public PaymentDto getPaymentsById(Long id) {
        Payment payment=paymentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Payment not found with id:" + id));
        return PaymentMapper.mapToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> getPaymentsByUserId(Long userId) {
        User user=userRepository.findById(userId)
                .orElseThrow((()->new ResourceNotFoundException("User not found with id:" + userId)));
        List<Payment> payments=paymentRepository.findPaymentByUserId(user.getId());
        return payments.stream().map(PaymentMapper::mapToPaymentDto).toList();
    }
}
