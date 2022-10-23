package pe.edu.user.userservice.user.resource;

import lombok.*;
import pe.edu.user.userservice.user.domain.model.nums.Gender;
import pe.edu.user.userservice.user.domain.model.nums.Status;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UserOwnerResource {
    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String email;
    private String password;
    private String phone_number;
    private String photo;
    private Status status;
    private Long score;
    private String country ;
    private String city ;
    private String address ;
}