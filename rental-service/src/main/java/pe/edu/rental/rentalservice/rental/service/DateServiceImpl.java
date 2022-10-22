package pe.edu.rental.rentalservice.rental.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.rental.rentalservice.rental.domain.entity.Date;
import pe.edu.rental.rentalservice.rental.domain.persistance.DateRepository;
import pe.edu.rental.rentalservice.rental.domain.service.DateService;
import pe.edu.rental.rentalservice.shared.exception.ResourceNotFoundException;
import pe.edu.rental.rentalservice.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class DateServiceImpl implements DateService {

    private static final String ENTITY = "Date";

    private final DateRepository dateRepository;

    //private final PublicationRepository publicationRepository;

    //private final UserTenantRepository userTenantRepository;

    private final Validator validator;

    public DateServiceImpl(DateRepository dateRepository, Validator validator) {
        this.dateRepository = dateRepository;
        this.validator = validator;
    }

    @Override
    public List<Date> getAll() {
        return dateRepository.findAll();
    }

    @Override
    public Date getById(Long dateId) {
        return dateRepository.findById(dateId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }

    @Override
    public List<Date> getAllByPublicationId(Long publicationId) {
        return dateRepository.findByPublicationId(publicationId);
    }

    @Override
    public List<Date> getAllByTenantId(Long tenantId) {
        return dateRepository.findByTenantId(tenantId);
    }

    @Override
    public Date create(Date date) {
        Set<ConstraintViolation<Date>> violations = validator.validate(date);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        /*
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

         return userTenantRepository.findById(tenantId).map(tenant -> {
            date.setPublication(publicationExisting.get());
            date.setTenant(tenant);
            return dateRepository.save(date);
        }).orElseThrow(() -> new ResourceNotFoundException("Tenant", tenantId));
        */

        return dateRepository.save(date);
    }

    @Override
    public Date update(Long dateId, Date date) {
        Set<ConstraintViolation<Date>> violations = validator.validate(date);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        /*
        if(!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", publicationId);

        if(!userTenantRepository.existsById(tenantId))
            throw new ResourceNotFoundException("Tenant", tenantId);
        */

        return dateRepository.findById(dateId).map(existingDate ->
                dateRepository.save(
                        existingDate.withStartDate(date.getStartDate())
                                .withMonths(date.getMonths())
                                .withPhoneNumber(date.getPhoneNumber())
                                .withEmail(date.getEmail())
                                .withDescription(date.getDescription())
                                .withStatus(date.isStatus())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }

    @Override
    public ResponseEntity<?> delete(Long dateId) {
        return dateRepository.findById(dateId).map(date -> {
            dateRepository.delete(date);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
    }
}
