package com.satish.service.impl;

import com.satish.dto.SubscriptionPlanDto;
import com.satish.entity.SubscriptionPlan;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.SubscriptionPlanMapper;
import com.satish.repository.SubscriptionPlanRepository;
import com.satish.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlanDto createSubscriptionPlan(SubscriptionPlanDto subscriptionPlanDto) {

        SubscriptionPlan subscriptionPlan = SubscriptionPlanMapper.mapToSubscriptionPlan(subscriptionPlanDto);

        SubscriptionPlan savedSubscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
        return SubscriptionPlanMapper.mapToSubscriptionPlanDto(savedSubscriptionPlan);
    }

    @Override
    public List<SubscriptionPlanDto> getAllSubscriptionPlan() {
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanRepository.findAll();
        return subscriptionPlans.stream()
                .map(SubscriptionPlanMapper::mapToSubscriptionPlanDto)
                .toList();
    }

    @Override
    public SubscriptionPlanDto getSubscriptionPlanById(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription Plan not found..!!"));
        return SubscriptionPlanMapper.mapToSubscriptionPlanDto(subscriptionPlan);
    }

    @Override
    public SubscriptionPlanDto updateSubscriptionPlan(Long id, SubscriptionPlanDto subscriptionPlanDto) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found"));

        subscriptionPlan.setName(subscriptionPlanDto.getName());
        subscriptionPlan.setDescription(subscriptionPlanDto.getDescription());

        SubscriptionPlan savedSubscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
        return SubscriptionPlanMapper.mapToSubscriptionPlanDto(savedSubscriptionPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(()->new ResourceNotFoundException("Subscription Plan not found"));
        subscriptionPlan.setIsActive(false);
        subscriptionPlanRepository.save(subscriptionPlan);
    }

}
