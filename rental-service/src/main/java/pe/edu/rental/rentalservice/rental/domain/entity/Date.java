package pe.edu.rental.rentalservice.rental.domain.entity;

import lombok.*;
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
    private java.util.Date start_date;

    @NotNull
    private int months;

    @NotNull
    private int phone_number;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String description;

    private boolean status;

    private Long publication_id;

    private Long tenant_id;

    //Relationships
    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private UserTenant tenant;
    */

}