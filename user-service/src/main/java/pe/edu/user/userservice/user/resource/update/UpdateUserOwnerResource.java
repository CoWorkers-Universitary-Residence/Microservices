package pe.edu.user.userservice.user.resource.update;

import lombok.*;
import pe.edu.user.userservice.user.domain.model.nums.Gender;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UpdateUserOwnerResource {
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String email;
    private String password;
    private String score;
    private String phone_number;
    private String photo;
    private String country ;
    private String city ;
    private String address ;
    private Long subscriptionId;
}