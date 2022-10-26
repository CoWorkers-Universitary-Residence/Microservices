package pe.edu.coworkers.publicationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.coworkers.publicationservice.model.UserOwner;

@FeignClient(name = "user-service", decode404 = true)
public interface UserOwnerClient {
    @GetMapping( "/api/v1/usersowner/{id}")
    public ResponseEntity<UserOwner> getAOwnerById(@PathVariable Long id);
}
