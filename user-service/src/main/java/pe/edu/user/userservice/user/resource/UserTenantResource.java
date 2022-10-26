package pe.edu.user.userservice.user.resource;

import lombok.*;
import pe.edu.user.userservice.user.domain.model.nums.Gender;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UserTenantResource {
    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String photo;
    private String email;
    private String occupation;
    private String password;
    private String phone_number;
    private String country ;
    private String city ;
}