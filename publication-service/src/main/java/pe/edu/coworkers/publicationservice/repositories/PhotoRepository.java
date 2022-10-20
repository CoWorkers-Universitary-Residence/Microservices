package pe.edu.coworkers.publicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByPublication(Publication publication);
}
