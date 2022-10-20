package pe.edu.coworkers.publicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.coworkers.publicationservice.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
