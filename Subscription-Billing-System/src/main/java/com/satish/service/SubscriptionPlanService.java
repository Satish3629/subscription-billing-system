package com.satish.service;

import com.satish.dto.SubscriptionPlanDto;

import java.util.List;

public interface SubscriptionPlanService {

    SubscriptionPlanDto createSubscriptionPlan (SubscriptionPlanDto subscriptionPlanDto);

    List<SubscriptionPlanDto> getAllSubscriptionPlan();

    SubscriptionPlanDto getSubscriptionPlanById(Long id);

    SubscriptionPlanDto updateSubscriptionPlan (Long id, SubscriptionPlanDto subscriptionPlanDto);

    void deleteSubscriptionPlan(Long subscriptionPlanId);

}
