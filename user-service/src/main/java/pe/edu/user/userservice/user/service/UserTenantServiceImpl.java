package pe.edu.user.userservice.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.user.userservice.shared.exception.ResourceNotFoundException;
import pe.edu.user.userservice.shared.exception.ResourceValidationException;
import pe.edu.user.userservice.user.domain.model.entities.UserTenant;
import pe.edu.user.userservice.user.domain.persistence.UserTenantRepository;
import pe.edu.user.userservice.user.domain.service.UserTenantService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserTenantServiceImpl implements UserTenantService {
    private static final String ENTITY = "UserTenant";
    private final UserTenantRepository userRepository;
    private final Validator validator;

    public UserTenantServiceImpl(UserTenantRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public UserTenant findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public UserTenant authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceValidationException(ENTITY, "%s %s".formatted(email, password)));
    }

    @Override
    public UserTenant register(UserTenant request) {
        Set<ConstraintViolation<UserTenant>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceValidationException("There is already a UserTenant with the email: %s".formatted(request.getEmail()));

        if (userRepository.existsByNameAndLastName(request.getName(), request.getLastName()))
            throw new ResourceValidationException("There is already a UserTenant with the name and lastname: %s %s".formatted(request.getName(), request.getLastName()));

        return userRepository.save(request);
    }

    @Override
    public UserTenant update(Long userId, UserTenant request) {
        UserTenant auxUserTenant = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

        request.setPassword(auxUserTenant.getPassword());

        Set<ConstraintViolation<UserTenant>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user ->
                userRepository.save(user.withName(request.getName())
                        .withLastName(request.getLastName())
                        .withEmail(request.getEmail())
                        .withPassword(request.getPassword()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

}