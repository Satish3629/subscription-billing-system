package com.satish.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="subscriptions")
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private  Boolean isActive;

    public Subscriptions(Long id, Long userId, Long planId, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.isActive=isActive;
    }

}
