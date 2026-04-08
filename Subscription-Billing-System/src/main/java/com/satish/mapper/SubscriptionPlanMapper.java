package com.satish.mapper;

import com.satish.dto.SubscriptionPlanDto;
import com.satish.entity.SubscriptionPlan;

public class SubscriptionPlanMapper {

    public static SubscriptionPlan mapToSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto){
        return new SubscriptionPlan(
                subscriptionPlanDto.getId(),
                subscriptionPlanDto.getName(),
                subscriptionPlanDto.getDescription(),
                subscriptionPlanDto.getPrice(),
                subscriptionPlanDto.getIsActive()
        );
    }

    public static SubscriptionPlanDto mapToSubscriptionPlanDto(SubscriptionPlan subscriptionPlan){
        return new SubscriptionPlanDto(
                subscriptionPlan.getId(),
                subscriptionPlan.getName(),
                subscriptionPlan.getDescription(),
                subscriptionPlan.getPrice(),
                subscriptionPlan.getIsActive()
        );
    }

}
