package com.satish.service;

import com.satish.dto.SubscriptionsDto;

import java.util.List;

public interface SubscriptionsService {

    SubscriptionsDto createSubscriptions(SubscriptionsDto subscriptionsDto);

    List<SubscriptionsDto> getAllSubscriptions();

    SubscriptionsDto getSubscriptionsById(Long subscriptionId);

    List<SubscriptionsDto> getSubscriptionsByUserId(Long userId);

    SubscriptionsDto updateSubscriptions (Long id, SubscriptionsDto subscriptionsDto);

    void deleteSubscriptions(Long subscriptionsId);

}

