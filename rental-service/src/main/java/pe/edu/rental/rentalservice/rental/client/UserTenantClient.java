package pe.edu.rental.rentalservice.rental.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.rental.rentalservice.rental.model.UserTenantResource;

@FeignClient(name = "user-service", decode404 = true)
public interface UserTenantClient {
    @GetMapping("/api/v1/userstenant/{id}")
    public ResponseEntity<UserTenantResource> getATenantById(@PathVariable Long id);
}
