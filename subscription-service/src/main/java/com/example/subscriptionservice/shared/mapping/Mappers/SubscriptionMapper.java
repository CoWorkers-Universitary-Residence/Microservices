package com.example.subscriptionservice.shared.mapping.Mappers;

import com.example.subscriptionservice.domain.model.entity.Subscription;
import com.example.subscriptionservice.resources.SubscriptionResource;
import com.example.subscriptionservice.resources.create.CreateSubscriptionResource;
import com.example.subscriptionservice.resources.update.UpdateSubscriptionResource;
import com.example.subscriptionservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class SubscriptionMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    public SubscriptionResource toResource(Subscription model){
        return mapper.map(model,SubscriptionResource.class);
    }
    public Subscription toModel(CreateSubscriptionResource resource){
        return mapper.map(resource ,Subscription.class);
    }
    public Subscription toModel(UpdateSubscriptionResource resource){
        return   mapper.map(resource, Subscription.class);
    }

}
