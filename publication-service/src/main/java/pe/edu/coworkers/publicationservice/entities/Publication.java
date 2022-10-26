package pe.edu.coworkers.publicationservice.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "publications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 700, nullable = false)
    private String about;

    @NotNull
    private double price;

    @NotNull
    private double escrow;

    @Column(length = 40, nullable = false)
    private String extra_expenses;

    @Column(length = 10, nullable = false)
    private String gender;

    @NotNull
    private boolean availability;

    @NotNull
    private int rooms;

    @Column(length = 15, nullable = false)
    private String bathrooms;

    @NotNull
    private float width;

    @NotNull
    private float height;

    @Column(length = 30, nullable = false)
    private String country;

    @Column(length = 30, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String address;

    @NotNull
    private boolean visit;

    @NotNull
    private int views;

    @NotNull
    private Long ownerId;

    private float score;

    @NotNull
    private int reviews;
}
