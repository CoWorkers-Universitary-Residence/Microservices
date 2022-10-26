package pe.edu.user.userservice;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.user.userservice.user.domain.model.entities.UserTenant;
import pe.edu.user.userservice.user.domain.model.nums.Gender;
import pe.edu.user.userservice.user.domain.persistence.UserTenantRepository;

import java.util.Date;

@SpringBootTest
public class UserTenantRepositoryMockTest {
    @Mock
    private UserTenantRepository userTenantRepository;

    @Test
    public void whenUserTenantRegister(){
        UserTenant userTenant = UserTenant.builder()
                .name("Carlos")
                .lastName("Campos")
                .gender(Gender.M)
                .date_of_birth(new Date())
                .description("This is a description")
                .photo("URL")
                .email("prueba2@hotmail.com")
                .occupation("Student")
                .password("123456")
                .phone_number(" 934 242 123")
                .country("Peru")
                .city("Lima")
                .build();
        userTenantRepository.save(userTenant);




    }


}
