package com.satish.controller;

import com.satish.dto.SubscriptionPlanDto;
import com.satish.service.SubscriptionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription_plans")
@RequiredArgsConstructor
@Tag(name="Subscription Plan", description = "Subscription plan module API's")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @PostMapping
    @Operation(summary = "Create Subscription Plan (ADMIN only)",
            description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubscriptionPlanDto> createSubscriptionPlan (@RequestBody SubscriptionPlanDto subscriptionPlanDto){
        SubscriptionPlanDto savedSubscriptionPlanDto = subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDto);
        return new ResponseEntity<>(savedSubscriptionPlanDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Subscription Plan",
            description = "🔒 Accessible by all role")
    public ResponseEntity<List<SubscriptionPlanDto>> getAllSubscriptionPlan(){
        List<SubscriptionPlanDto> subscriptionPlanDto = subscriptionPlanService.getAllSubscriptionPlan();
        return ResponseEntity.ok(subscriptionPlanDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get SubscriptionPlan by Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<SubscriptionPlanDto> getSubscriptionPlanById(@PathVariable Long id){
        SubscriptionPlanDto subscriptionPlanDto = subscriptionPlanService.getSubscriptionPlanById(id);
        return ResponseEntity.ok(subscriptionPlanDto);
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Update Subscription Plan (ADMIN only)",
            description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubscriptionPlanDto> updateSubscriptionPlan (@PathVariable("id") Long subscriptionPlanId,
                                                                       @RequestBody SubscriptionPlanDto subscriptionPlanDto){
        SubscriptionPlanDto savedSubscriptionPlanDto = subscriptionPlanService.updateSubscriptionPlan(subscriptionPlanId, subscriptionPlanDto);
        return ResponseEntity.ok(savedSubscriptionPlanDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subscription Plan (ADMIN only)",
            description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSubscriptionPlan (@PathVariable("id") Long subscriptionPlanId){
        subscriptionPlanService.deleteSubscriptionPlan(subscriptionPlanId);
        return ResponseEntity.ok("Subscription Deleted successfully");
    }

}
