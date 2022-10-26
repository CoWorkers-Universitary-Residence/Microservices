package pe.edu.coworkers.reviewservice.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    Review getById(Long reviewId);
    List<Review> getByPublicationId(Long publicationId);
    Review create(Review request);
    Review update(Long reviewId, Review request);
    ResponseEntity<?> delete(Long reviewId);
}
