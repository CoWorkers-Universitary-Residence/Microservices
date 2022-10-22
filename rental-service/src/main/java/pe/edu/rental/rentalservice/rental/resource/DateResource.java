package pe.edu.rental.rentalservice.rental.resource;

import lombok.*;

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

    private Long publicationId;
    private Long tenantId;

    //Relationships
    //private PublicationResource publication;
    //private UserTenantResource tenant;
}
