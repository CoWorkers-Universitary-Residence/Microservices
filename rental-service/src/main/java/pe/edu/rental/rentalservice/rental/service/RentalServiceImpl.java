package pe.edu.rental.rentalservice.rental.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.rental.rentalservice.rental.domain.entity.Rental;
import pe.edu.rental.rentalservice.rental.domain.persistance.DateRepository;
import pe.edu.rental.rentalservice.rental.domain.persistance.RentalRepository;
import pe.edu.rental.rentalservice.rental.domain.service.RentalService;
import pe.edu.rental.rentalservice.shared.exception.ResourceNotFoundException;
import pe.edu.rental.rentalservice.shared.exception.ResourceValidationException;

import javax.swing.text.html.Option;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentalServiceImpl implements RentalService {

    private static final String ENTITY = "Rental";
    private final DateRepository dateRepository;
    private final RentalRepository rentalRepository;
    private final Validator validator;

    public RentalServiceImpl(DateRepository dateRepository, RentalRepository rentalRepository, Validator validator) {
        this.dateRepository = dateRepository;
        this.rentalRepository = rentalRepository;
        this.validator = validator;
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getById(Long rentalId) {
        return rentalRepository.findById(rentalId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentalId));
    }

    @Override
    public Rental getByDateId(Long dateId) {
        return rentalRepository.findByDateId(dateId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }

    @Override
    public Rental create(Long dateId, Rental rental) {
        Set<ConstraintViolation<Rental>> violations = validator.validate(rental);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!dateRepository.existsById(dateId)) {
            throw new ResourceNotFoundException("Date", dateId);
        }
        else {
            rental.setDate(dateRepository.findById(dateId).get());
        }

        Optional<Rental> rentalExisting = rentalRepository.findByDateId(dateId);

        if(!rentalExisting.isEmpty())
            throw new ResourceValidationException(String.format("A rental entity already exists with id %d", rental.getId()));

        return rentalRepository.save(rental);
    }

    @Override
    public Rental update(Long rentalId, Rental rental) {
        Set<ConstraintViolation<Rental>> violations = validator.validate(rental);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return rentalRepository.findById(rentalId).map(existingRental ->
                rentalRepository.save(existingRental.withPrice(rental.getPrice())
                        .withRegisterDate(rental.getRegisterDate())
                        .withStartDate(rental.getStartDate())
                        .withFinishDate(rental.getFinishDate())
                )
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentalId));
    }

    @Override
    public ResponseEntity<?> delete(Long rentalId) {
        return rentalRepository.findById(rentalId).map(rental -> {
            rentalRepository.delete(rental);
            return  ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentalId));
    }
}
