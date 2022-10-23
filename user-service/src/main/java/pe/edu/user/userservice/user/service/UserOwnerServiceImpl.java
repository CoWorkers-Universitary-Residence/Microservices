package pe.edu.user.userservice.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.user.userservice.shared.exception.ResourceNotFoundException;
import pe.edu.user.userservice.shared.exception.ResourceValidationException;
import pe.edu.user.userservice.user.domain.model.entities.UserOwner;
import pe.edu.user.userservice.user.domain.persistence.UserOwnerRepository;
import pe.edu.user.userservice.user.domain.service.UserOwnerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserOwnerServiceImpl implements UserOwnerService {
    private static final String ENTITY = "UserOwner";
    private final UserOwnerRepository userRepository;
    private final Validator validator;

    public UserOwnerServiceImpl(UserOwnerRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public UserOwner authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceValidationException(ENTITY, "%s %s".formatted(email, password)));
    }

    @Override
    public UserOwner register(UserOwner request) {
        Set<ConstraintViolation<UserOwner>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceValidationException("There is already a UserOwner with the email: %s".formatted(request.getEmail()));

        if (userRepository.existsByNameAndLastName(request.getName(), request.getLastName()))
            throw new ResourceValidationException("There is already a UserOwner with the name and lastname: %s %s".formatted(request.getName(), request.getLastName()));

        return userRepository.save(request);
    }

    @Override
    public UserOwner update(Long userId, UserOwner request) {
        UserOwner auxUserOwner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

        request.setPassword(auxUserOwner.getPassword());

        Set<ConstraintViolation<UserOwner>> violations = validator.validate(request);

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
