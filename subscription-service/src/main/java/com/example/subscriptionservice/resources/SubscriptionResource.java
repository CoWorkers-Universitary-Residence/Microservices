package com.example.subscriptionservice.resources;

import com.example.subscriptionservice.domain.model.enums.Type;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
