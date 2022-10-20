package pe.edu.coworkers.publicationservice.services;

import org.springframework.http.ResponseEntity;
import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;
import java.util.Optional;

public interface PhotoService {
    List<Photo> getAll();
    Photo getById(Long id);
    Photo create(Photo photo);
    Photo update(Photo photo);
    Optional<ResponseEntity<?>> delete(Long photoId);
    List<Photo> findByPublication(Publication publication);
}
