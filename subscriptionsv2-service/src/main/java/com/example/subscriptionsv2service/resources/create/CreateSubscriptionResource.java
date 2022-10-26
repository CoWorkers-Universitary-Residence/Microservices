package com.example.subscriptionsv2service.resources.create;

import com.example.subscriptionsv2service.domain.model.enums.Type;
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
public class CreateSubscriptionResource {
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
