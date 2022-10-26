package pe.edu.rental.rentalservice.rental.domain.entity;

import lombok.*;
import pe.edu.rental.rentalservice.rental.model.Publication;
import pe.edu.rental.rentalservice.rental.model.UserTenantResource;
import pe.edu.rental.rentalservice.shared.domain.model.entity.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dates")
public class Date extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private boolean status;

    private Long publicationId;

    private Long tenantId;

    //Relationships
    @Transient
    private Publication publication;

    @Transient
    private UserTenantResource userTenantResource;

}