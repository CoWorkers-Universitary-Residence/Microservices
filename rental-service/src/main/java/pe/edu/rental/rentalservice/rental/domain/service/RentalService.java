package pe.edu.rental.rentalservice.rental.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.rental.rentalservice.rental.domain.entity.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> getAll();
    Rental getById(Long rentalId);
    Rental getByDateId(Long dateId);

    Rental create(Long dateId, Rental rental);
    Rental update(Long rentalId, Rental rental);
    ResponseEntity<?> delete(Long rentalId);

}
