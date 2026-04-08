package com.satish.repository;


import com.satish.entity.Payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Page<Payment> findByStatus(String status, Pageable pageable);

    List<Payment> findPaymentByUserId(Long userId);

}


