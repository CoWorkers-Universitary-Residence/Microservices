package pe.edu.coworkers.reviewservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.coworkers.reviewservice.domain.model.model.UpdateUserTenantResource;

@FeignClient(name = "user-service", decode404 = true)
public interface TenantClient {
    @GetMapping("/sign-in")
    public UpdateUserTenantResource authenticateUserTenant(@RequestParam(name = "email") String email,
                                                           @RequestParam(name = "password") String password);
}
