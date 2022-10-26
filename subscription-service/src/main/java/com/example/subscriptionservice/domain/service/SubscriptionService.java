package com.example.subscriptionservice.domain.service;

import com.example.subscriptionservice.domain.model.entity.Subscription;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    Subscription getById(Long subscriptionId);
    Subscription create (Subscription request);
    Subscription update(Long subscriptionId,Subscription request);
    ResponseEntity<?> delete(Long subscriptionId);
}
