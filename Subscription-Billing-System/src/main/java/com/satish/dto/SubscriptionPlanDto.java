package com.satish.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPlanDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;

}
