package pe.edu.rental.rentalservice.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.rental.rentalservice.rental.client.PublicationClient;
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

    @Autowired
    PublicationClient publicationClient;

    //private final UserTenantRepository userTenantRepository;

    private final Validator validator;

    public DateServiceImpl(DateRepository dateRepository, Validator validator) {
        this.dateRepository = dateRepository;
        this.validator = validator;
    }

    @Override
    public List<Date> getAll() {
        List<Date> result = dateRepository.findAll();
        result.forEach(
                (date) -> date.setPublication(publicationClient.getPublication(date.getPublicationId()).getBody())
        );
        return result;
    }

    @Override
    public Date getById(Long dateId) {
        Date result = dateRepository.findById(dateId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, dateId));
        result.setPublication(publicationClient.getPublication(result.getPublicationId()).getBody());
        return result;
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

        if(publicationClient.getPublication(date.getPublicationId()).getStatusCodeValue() == 404)
            throw new ResourceNotFoundException("Publication", date.getPublicationId());

        //TODO: Verify validation
        if(dateRepository.findByTenantId(date.getTenantId()).size() > 0)
            throw new ResourceValidationException("There is already a date for this publication");

        /*
         return userTenantRepository.findById(tenantId).map(tenant -> {
            date.setPublication(publicationExisting.get());
            date.setTenant(tenant);
            return dateRepository.save(date);
        }).orElseThrow(() -> new ResourceNotFoundException("Tenant", tenantId));
        */

        date.setPublication(publicationClient.getPublication(date.getPublicationId()).getBody());

        return dateRepository.save(date);
    }

    @Override
    public Date update(Long dateId, Date date) {
        Set<ConstraintViolation<Date>> violations = validator.validate(date);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(publicationClient.getPublication(date.getPublicationId()).getStatusCodeValue() == 404)
            throw new ResourceNotFoundException("Publication", date.getPublicationId());

        /*
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
