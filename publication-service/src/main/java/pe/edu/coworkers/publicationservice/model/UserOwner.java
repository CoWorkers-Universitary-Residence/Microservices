package pe.edu.coworkers.publicationservice.model;

import lombok.Data;
import pe.edu.coworkers.publicationservice.model.nums.Gender;
import pe.edu.coworkers.publicationservice.model.nums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class UserOwner {
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
