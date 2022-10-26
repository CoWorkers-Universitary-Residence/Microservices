package pe.edu.user.userservice.user.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.user.userservice.user.domain.model.entities.UserTenant;

public interface UserTenantService {
    UserTenant authenticate(String email, String password);
    UserTenant register(UserTenant request);
    UserTenant update(Long userId, UserTenant request);
    ResponseEntity<?> delete(Long userId);
}
