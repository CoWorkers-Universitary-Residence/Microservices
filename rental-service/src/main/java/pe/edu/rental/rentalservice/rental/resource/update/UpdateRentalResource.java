package pe.edu.rental.rentalservice.rental.resource.update;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalResource {
    private Long id;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @Temporal(TemporalType.DATE)
    private java.util.Date registerDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private java.util.Date finishDate;

    @NotNull
    @Positive
    private int months;
}
