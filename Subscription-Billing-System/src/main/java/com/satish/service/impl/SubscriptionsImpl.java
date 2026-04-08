package com.satish.service.impl;

import com.satish.dto.SubscriptionsDto;
import com.satish.entity.SubscriptionPlan;
import com.satish.entity.Subscriptions;
import com.satish.entity.User;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.SubscriptionsMapper;
import com.satish.repository.SubscriptionPlanRepository;
import com.satish.repository.SubscriptionsRepository;
import com.satish.repository.UserRepository;
import com.satish.service.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionsImpl implements SubscriptionsService {

    private final SubscriptionsRepository subscriptionsRepository;
    private final UserRepository userRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionsDto createSubscriptions(SubscriptionsDto subscriptionsDto) {

        Subscriptions subscriptions = SubscriptionsMapper.mapToSubscriptions(subscriptionsDto);

        User user = userRepository.findById(subscriptionsDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(subscriptionsDto.getPlanId())
                .orElseThrow(()->new ResourceNotFoundException("Plan not found..!!"));

        subscriptions.setUser(user);
        subscriptions.setSubscriptionPlan(subscriptionPlan);

        Subscriptions savedSubscriptions = subscriptionsRepository.save(subscriptions);
        return SubscriptionsMapper.mapToSubscriptionsDto(savedSubscriptions);
    }

    @Override
    public List<SubscriptionsDto> getAllSubscriptions() {
        List<Subscriptions> subscriptions = subscriptionsRepository.findAll();
        List<SubscriptionsDto> subscriptionsDto = subscriptions.stream()
                .map(SubscriptionsMapper::mapToSubscriptionsDto)
                .toList();
        return subscriptionsDto;
    }

    @Override
    public SubscriptionsDto getSubscriptionsById(Long subscriptionId) {
        Subscriptions subscriptions=subscriptionsRepository.findById(subscriptionId)
                .orElseThrow(()->new ResourceNotFoundException("Subscriptions not found..!!"));
        return SubscriptionsMapper.mapToSubscriptionsDto(subscriptions);
    }

    @Override
    public List<SubscriptionsDto> getSubscriptionsByUserId(Long userId) {

        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found..!!"));

        List<Subscriptions> subscriptions=subscriptionsRepository.findSubscriptionsByUserId(user.getId());

        return subscriptions.stream()
                .map(SubscriptionsMapper::mapToSubscriptionsDto)
                .toList();
    }

    @Override
    public SubscriptionsDto updateSubscriptions(Long id, SubscriptionsDto subscriptionsDto) {

        Subscriptions subscriptions = subscriptionsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found"));

        User user = userRepository.findById(subscriptionsDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));

        SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(subscriptionsDto.getPlanId())
                .orElseThrow(()->new ResourceNotFoundException("Plan not found..!!"));

        subscriptions.setUser(user);
        subscriptions.setSubscriptionPlan(subscriptionPlan);
        Subscriptions savedSubscriptions = subscriptionsRepository.save(subscriptions);

        return SubscriptionsMapper.mapToSubscriptionsDto(savedSubscriptions);
    }

    @Override
    public void deleteSubscriptions(Long subscriptionsId) {
        Subscriptions subscriptions =subscriptionsRepository.findById(subscriptionsId).orElseThrow(()-> new ResourceNotFoundException("Subscription not found"));
        subscriptions.setIsActive(false);
        subscriptionsRepository.save(subscriptions);
    }



}
