package com.example.subscriptionservice.api;

import com.example.subscriptionservice.domain.model.entity.Subscription;
import com.example.subscriptionservice.domain.service.SubscriptionService;
import com.example.subscriptionservice.resources.SubscriptionResource;
import com.example.subscriptionservice.resources.create.CreateSubscriptionResource;
import com.example.subscriptionservice.resources.update.UpdateSubscriptionResource;
import com.example.subscriptionservice.shared.mapping.Mappers.SubscriptionMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionController(SubscriptionService subscriptionService,SubscriptionMapper subscriptionMapper  ){
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }
    @GetMapping("{subscriptionId}")
    public SubscriptionResource getSubscriptionById(@PathVariable Long subscriptionId) {
        return subscriptionMapper.toResource(subscriptionService.getById(subscriptionId));
    }

    @PostMapping()
    public SubscriptionResource createSubscription(@RequestParam(name = "userId") Long userId,
                                   @Valid @RequestBody CreateSubscriptionResource request) {
        return subscriptionMapper.toResource(subscriptionService.create(subscriptionMapper.toModel(request)));
    }

    @PutMapping("{subscriptionId}")
    public SubscriptionResource updateSubscription(@PathVariable Long subscriptionId,
                                   @Valid @RequestBody UpdateSubscriptionResource request) {
        return subscriptionMapper.toResource(subscriptionService.update(subscriptionId, subscriptionMapper.toModel(request)));
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId) {
        return subscriptionService.delete(subscriptionId);
    }
}

