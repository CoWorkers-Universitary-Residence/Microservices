package pe.edu.coworkers.publicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.coworkers.publicationservice.entities.Detail;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByPublication(Publication publication);
}
