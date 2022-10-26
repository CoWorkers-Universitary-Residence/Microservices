package pe.edu.rental.rentalservice.rental.model;

import lombok.Data;
import pe.edu.rental.rentalservice.rental.model.nums.Gender;

import java.util.Date;

@Data
public class UserTenantResource {
    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Date date_of_birth;
    private String description;
    private String photo;
    private String email;
    private String password;
    private String phone_number;
    private String country ;
    private String city ;
}
