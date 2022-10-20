package pe.edu.coworkers.publicationservice.services;

import org.springframework.http.ResponseEntity;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {
    List<Publication> getAll();
    Publication getById(Long id);
    Publication create(Publication publication);
    Publication update(Publication publication);
    Optional<ResponseEntity<?>> delete(Long publicationId);
}
