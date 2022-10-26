package com.example.subscriptionsv2service.shared.mapping;

import com.example.subscriptionsv2service.shared.mapping.Mappers.SubscriptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("challengeModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }

    @Bean
    public SubscriptionMapper subscriptionMapper () { return  new SubscriptionMapper();}
}
