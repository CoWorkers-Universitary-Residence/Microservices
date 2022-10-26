package pe.edu.rental.rentalservice.rental.domain.entity;

import lombok.*;
import pe.edu.rental.rentalservice.shared.domain.model.entity.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //Relationships
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "date_id", nullable = false)
    private Date date;
}