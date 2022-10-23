package pe.edu.user.userservice.user.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.user.userservice.user.domain.model.entities.UserTenant;

import java.util.Optional;

@Repository
public interface UserTenantRepository extends JpaRepository<UserTenant, Long> {
    Optional<UserTenant> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name, String lastname);
    Boolean existsByEmail(String email);
}
