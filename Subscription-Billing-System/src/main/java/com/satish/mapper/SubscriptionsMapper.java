package com.satish.mapper;

import com.satish.dto.SubscriptionsDto;
import com.satish.entity.Subscriptions;


public class SubscriptionsMapper {
    public static Subscriptions mapToSubscriptions(SubscriptionsDto subscriptionsDto){
        return new Subscriptions(
                subscriptionsDto.getId(),
                subscriptionsDto.getUserId(),
                subscriptionsDto.getPlanId(),
                subscriptionsDto.getStartDate(),
                subscriptionsDto.getEndDate(),
                subscriptionsDto.getIsActive()
    );
    }

    public static SubscriptionsDto mapToSubscriptionsDto(Subscriptions subscriptions){
        return new SubscriptionsDto(
                subscriptions.getId(),
                subscriptions.getUser() != null ? subscriptions.getUser().getId() : null,
                subscriptions.getSubscriptionPlan() != null ? subscriptions.getSubscriptionPlan().getId() : null,
                subscriptions.getStartDate(),
                subscriptions.getEndDate(),
                subscriptions.getIsActive()
        );
    }

}
