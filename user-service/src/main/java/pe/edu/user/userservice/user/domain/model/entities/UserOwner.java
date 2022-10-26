package pe.edu.user.userservice.user.domain.model.entities;

import lombok.*;
import pe.edu.user.userservice.user.domain.model.nums.Gender;
import pe.edu.user.userservice.user.domain.model.nums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "owners")
public class UserOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotNull
    private Date date_of_birth;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String phone_number;

    @NotNull
    @NotBlank
    private String photo;

    @Enumerated(value = EnumType.STRING)
    private Status status;


    private Long score;

    @NotNull
    @NotBlank
    private String country ;

    @NotNull
    @NotBlank
    private String city ;

    @NotNull
    @NotBlank
    private String address ;
}
