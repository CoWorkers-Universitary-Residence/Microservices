package pe.edu.user.userservice.user.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.user.userservice.user.domain.model.entities.UserOwner;

public interface UserOwnerService {

    UserOwner findById(Long id);
    UserOwner authenticate(String email, String password);
    UserOwner register(UserOwner request);
    UserOwner update(Long userId, UserOwner request);
    ResponseEntity<?> delete(Long userId);
}
