package com.satish.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private Long userId;
    private Long subscriptionsId;
    private BigDecimal amount;
    private String transactionId;
    private Date paymentDate;
    private String status;

}
