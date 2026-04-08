package com.satish.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscriptions_id")
    private Subscriptions subscriptions;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Payment(Long id, Long userId, Long subscriptionsId, BigDecimal amount, String transactionID, Date paymentDate, String status) {
        this.id=id;
        this.amount=amount;
        this.transactionId=transactionID;
        this.paymentDate=paymentDate;
        this.status=status;
    }

}
