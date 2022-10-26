package com.example.subscriptionsv2service.resources;

import com.example.subscriptionservice.domain.model.enums.Type;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class SubscriptionResource {

    private Type type;
    private Date start_date;
    private Date finish_date;
    private Long price;
}
