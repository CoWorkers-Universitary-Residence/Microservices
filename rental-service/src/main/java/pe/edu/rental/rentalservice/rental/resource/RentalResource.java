package pe.edu.rental.rentalservice.rental.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RentalResource {
    private Long id;
    private Double price;
    private java.util.Date registerDate;
    private java.util.Date startDate;
    private java.util.Date finishDate;
    private int months;

    //Relationships
    private DateResource date;
}
