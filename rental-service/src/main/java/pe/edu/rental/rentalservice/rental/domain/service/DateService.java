package pe.edu.rental.rentalservice.rental.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.rental.rentalservice.rental.domain.entity.Date;

import java.util.List;

public interface DateService {
    List<Date> getAll();
    Date getById(Long dateId);
    List<Date> getAllByPublicationId(Long publicationId);
    List<Date> getAllByTenantId(Long tenantId);

    Date create(Date date);
    Date update(Long dateId, Date date);
    ResponseEntity<?> delete(Long dateId);
}
