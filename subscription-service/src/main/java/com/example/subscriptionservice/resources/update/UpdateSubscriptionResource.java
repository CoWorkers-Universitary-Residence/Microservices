package com.example.subscriptionservice.resources.update;

import com.example.subscriptionservice.domain.model.enums.Type;
import com.sun.istack.NotNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class UpdateSubscriptionResource {
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @NotNull
    private Date start_date;

    @NotNull
    private Date finish_date;

    @NotNull
    private Long price;
}
