package pe.edu.rental.rentalservice.rental.domain.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.rental.rentalservice.rental.domain.entity.Date;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

    List<Date> findByPublicationId(Long publicationId);
    List<Date> findByTenantId(Long tenantId);
    Optional<Date> findByIdAndPublicationIdAndTenantId(Long id, Long publicationId, Long tenantId);

}
