package pe.edu.coworkers.reviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.reviewservice.api.client.PublicationClient;
import pe.edu.coworkers.reviewservice.api.client.TenantClient;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;
import pe.edu.coworkers.reviewservice.domain.persistence.ReviewRepository;
import pe.edu.coworkers.reviewservice.domain.service.ReviewService;
import pe.edu.coworkers.reviewservice.shared.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String ENTITY = "Review";

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PublicationClient publicationClient;

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
        ResponseEntity<Publication> response = publicationClient.getPublication(publicationId);
        Publication publication = response.getBody();

        if (publication == null){
            throw new ResourceNotFoundException("Publication", publicationId);
        }

        return reviewRepository.findAllByPublicationId(publicationId);
    }

    @Override
    public Review create(Review request) {
        Long publicationId = request.getPublicationId();

        ResponseEntity<Publication> response = publicationClient.getPublication(publicationId);
        Publication publication = response.getBody();

        if (publication == null){
            throw new ResourceNotFoundException("Publication", publicationId);
        }

        return reviewRepository.save(request);
    }

    //TODO: Update Publication Score
    @Override
    public Review update(Long reviewId, Review request) {
        Long publicationId = request.getPublicationId();

        ResponseEntity<Publication> response = publicationClient.getPublication(publicationId);
        Publication publication = response.getBody();

        if (publication == null){
            throw new ResourceNotFoundException("Publication", publicationId);
        }

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
