package com.example.subscriptionservice.service;


import com.challenge.backend.shared.exception.ResourceValidationException;
import com.example.subscriptionservice.domain.model.entity.Subscription;
import com.example.subscriptionservice.domain.persistance.SubscriptionRepository;
import com.example.subscriptionservice.domain.service.SubscriptionService;
import com.example.subscriptionservice.shared.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final String Entity = "Subscription";
    private final SubscriptionRepository subscriptionRepository;
    private  final Validator validator;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, Validator validator){
        this.subscriptionRepository = subscriptionRepository;
        this.validator = validator;
    }



    @Override
    public Subscription getById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new ResourceNotFoundException(Entity,subscriptionId));
    }

    @Override
    public Subscription create(Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(Entity,violations);
        Subscription subscription = new Subscription();
        subscription = subscriptionRepository.save(subscription.withType(request.getType())
                .withStart_date(request.getStart_date())
                .withFinish_date(request.getFinish_date())
                        .withPrice(request.getPrice())
                );
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(Long subscriptionId, Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(Entity, violations);

        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            subscription.setType(request.getType());
            subscription.setStart_date(request.getStart_date());
            subscription.setFinish_date(request.getFinish_date());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(()-> new ResourceNotFoundException(Entity,subscriptionId));
    }

    @Override
    public ResponseEntity<?> delete(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(note -> {
            subscriptionRepository.delete(note);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(Entity, subscriptionId));
    }
}
