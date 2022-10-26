package pe.edu.user.userservice.user.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.user.userservice.user.domain.model.entities.UserOwner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserOwnerRepository extends JpaRepository<UserOwner, Long> {
    Optional<UserOwner> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name, @NotNull @NotBlank String lastName);
    Boolean existsByEmail(String email);
}
