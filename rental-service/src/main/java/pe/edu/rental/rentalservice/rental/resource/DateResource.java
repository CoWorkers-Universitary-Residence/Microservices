package pe.edu.rental.rentalservice.rental.resource;

import lombok.*;
import pe.edu.rental.rentalservice.rental.model.Publication;
import pe.edu.rental.rentalservice.rental.model.UserTenantResource;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DateResource {
    private Long id;
    private java.util.Date startDate;
    private int months;
    private int phoneNumber;
    private String email;
    private String description;
    private boolean status;
    private Long publicationId;
    private Long tenantId;

    //Relationships
    private Publication publication;
    private UserTenantResource tenant;
}
