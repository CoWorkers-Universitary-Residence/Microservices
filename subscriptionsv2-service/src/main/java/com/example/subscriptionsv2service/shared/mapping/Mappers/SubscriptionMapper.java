package com.example.subscriptionsv2service.shared.mapping.Mappers;

import com.example.subscriptionsv2service.domain.model.entity.Subscription;
import com.example.subscriptionsv2service.resources.SubscriptionResource;
import com.example.subscriptionsv2service.resources.create.CreateSubscriptionResource;
import com.example.subscriptionsv2service.resources.update.UpdateSubscriptionResource;
import com.example.subscriptionsv2service.shared.mapping.EnhancedModelMapper;
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
