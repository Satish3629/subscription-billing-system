package com.satish.controller;

import com.satish.dto.PaymentDto;
import com.satish.entity.Payment;
import com.satish.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/payment")
@RequiredArgsConstructor
@Tag(name="Payment", description = "Payment module API's")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Create Payment",
            description = "🔒 Accessible by all role")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto){
        PaymentDto savedPaymentDto = paymentService.createPayment(paymentDto);
        return new ResponseEntity<>(savedPaymentDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Payments (ADMIN only)",
            description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Payment>> getAllPayments(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(required = false) String status) {

        Page<Payment> payments = paymentService.getAllPayments(page, size, status);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Payment By Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<PaymentDto> getPaymentsById(@PathVariable Long id){
        PaymentDto paymentDto=paymentService.getPaymentsById(id);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get Payment By User Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<List<PaymentDto>> getPaymentByUserId(@PathVariable("id") Long userId){
        List<PaymentDto> paymentDto=  paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(paymentDto);
    }
}