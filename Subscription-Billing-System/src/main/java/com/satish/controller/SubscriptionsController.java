package com.satish.controller;


import com.satish.dto.SubscriptionsDto;
import com.satish.service.SubscriptionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name="Subscription", description = "Subscription module API's")
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @PostMapping
    @Operation(summary = "Create Subscription",
                description = "🔒 Accessible by all role")
    public ResponseEntity<SubscriptionsDto> createSubscriptions(@RequestBody SubscriptionsDto subscriptionsDto){
        SubscriptionsDto savedSubscriptionDto = subscriptionsService.createSubscriptions(subscriptionsDto);
        return new ResponseEntity<>(savedSubscriptionDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Subscriptions (ADMIN only)",
                description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SubscriptionsDto>> getAllSubscriptions(){
        List<SubscriptionsDto> subscriptionsDtos= subscriptionsService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptionsDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Subscription By Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<SubscriptionsDto> getSubscriptionById(@PathVariable("id")  Long subscriptionId){
        SubscriptionsDto subscriptionsDto=subscriptionsService.getSubscriptionsById(subscriptionId);
        return  ResponseEntity.ok(subscriptionsDto);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get Subscription By User Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<List<SubscriptionsDto>> getSubscriptionsByUserId(@PathVariable("id")  Long subscriptionId){
        List<SubscriptionsDto> subscriptionsDto=subscriptionsService.getSubscriptionsByUserId(subscriptionId);
        return  ResponseEntity.ok(subscriptionsDto);
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Update Subscription By Id",
            description = "🔒 Accessible by all role")
    public ResponseEntity<SubscriptionsDto> updateSubscriptions(@PathVariable("id")Long subscriptionsId, @RequestBody SubscriptionsDto subscriptionsDto){
        SubscriptionsDto savedSubscriptionsDto = subscriptionsService.updateSubscriptions(subscriptionsId,subscriptionsDto);
        return ResponseEntity.ok(savedSubscriptionsDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Subscription (ADMIN only)",
            description = "🔒 Accessible by admin role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSubscriptions (@PathVariable("id") Long subscriptionsId){
        subscriptionsService.deleteSubscriptions(subscriptionsId);
        return ResponseEntity.ok("Subscription deleted successfully");
    }

}
