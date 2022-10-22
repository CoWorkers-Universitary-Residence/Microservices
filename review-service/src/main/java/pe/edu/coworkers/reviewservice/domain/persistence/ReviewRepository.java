package pe.edu.coworkers.reviewservice.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPublicationId(Long publication_id);
}
