package pe.edu.rental.rentalservice.rental.resource.create;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateDateResource {
    @NotNull
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @NotNull
    private int months;

    @NotNull
    private int phoneNumber;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String description;

    private Long publicationId;

    private Long tenantId;
}