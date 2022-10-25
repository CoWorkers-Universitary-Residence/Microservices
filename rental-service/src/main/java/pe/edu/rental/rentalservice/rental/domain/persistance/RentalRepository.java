package pe.edu.rental.rentalservice.rental.domain.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.rental.rentalservice.rental.domain.entity.Rental;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findByDateId(Long dateId);
    Optional<Rental> findByIdAndDateId(Long id, Long dateId);
}
