package pe.edu.coworkers.reviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.persistence.ReviewRepository;
import pe.edu.coworkers.reviewservice.domain.service.ReviewService;
import pe.edu.coworkers.reviewservice.shared.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String ENTITY = "Review";

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, reviewId));
    }

    @Override
    public List<Review> getByPublicationId(Long publicationId) {
        return reviewRepository.findByPublicationId(publicationId);
    }

    @Override
    public Review create(Review request) {
        return reviewRepository.save(request);
    }

    @Override
    public Review update(Long reviewId, Review request) {
        return reviewRepository.findById(reviewId).map(review ->
                reviewRepository.save(
                        review.withComment(request.getComment())
                                .withScore(request.getScore())
                                .withDate(request.getDate())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reviewId));
    }

    @Override
    public ResponseEntity<?> delete(Long reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reviewId));
    }
}
