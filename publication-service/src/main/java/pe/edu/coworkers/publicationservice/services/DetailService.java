package pe.edu.coworkers.publicationservice.services;

import org.springframework.http.ResponseEntity;
import pe.edu.coworkers.publicationservice.entities.Detail;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    List<Detail> getAll();
    Detail getById(Long id);
    Detail create(Detail detail);
    Detail update(Detail detail);
    Optional<ResponseEntity<?>> delete(Long detailId);
    List<Detail> findByPublication(Publication publication);
}
