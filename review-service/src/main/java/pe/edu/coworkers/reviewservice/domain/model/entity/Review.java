package pe.edu.coworkers.reviewservice.domain.model.entity;

import lombok.*;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @NotNull(message = "Comment must not be empty")
    @NotBlank
    @Column(length = 150, nullable = false)
    private String comment;

    @NotNull
    @Column(nullable = false)
    private int score;

    @Transient
    private Long publicationId;

    //@Transient
    //private Tenant tenant;
}