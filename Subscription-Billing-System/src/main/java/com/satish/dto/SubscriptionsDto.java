package com.satish.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionsDto {

    private Long id;
    private Long userId;
    private Long planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

}
