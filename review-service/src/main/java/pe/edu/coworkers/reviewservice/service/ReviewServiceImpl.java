package pe.edu.coworkers.reviewservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.reviewservice.api.client.PublicationClient;
import pe.edu.coworkers.reviewservice.api.client.TenantClient;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;
import pe.edu.coworkers.reviewservice.domain.model.model.UserTenantResource;
import pe.edu.coworkers.reviewservice.domain.persistence.ReviewRepository;
import pe.edu.coworkers.reviewservice.domain.service.ReviewService;
import pe.edu.coworkers.reviewservice.shared.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String ENTITY = "Review";

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PublicationClient publicationClient;

    @Autowired
    private TenantClient tenantClient;

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
        // Publication Validation
        ResponseEntity<Publication> publicationResponse = publicationClient.getPublication(publicationId);
        Publication publication = publicationResponse.getBody();

        if (publication == null){
            throw new ResourceNotFoundException("Publication", publicationId);
        }

        return reviewRepository.findAllByPublicationId(publicationId);
    }

    @Override
    public Review create(Review request) {
        ValidateIfTenantExists(request);
        Publication publication = ValidateIfPublicationExists(request);


        float score = publication.getScore();
        int reviews_number = publication.getReviews();

        float new_score = (float) ((score * reviews_number + (float)request.getScore()) / ((float)reviews_number + 1.0));
        int new_reviews_number = reviews_number + 1;

        publication.setScore(new_score);
        publication.setReviews(new_reviews_number);

        publicationClient.updatePublication(publication.getId(), publication);

        return reviewRepository.save(request);
    }

    @Override
    public Review update(Long reviewId, Review request) {
        ValidateIfTenantExists(request);
        ValidateIfPublicationExists(request);

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

    private void ValidateIfTenantExists(Review request){
        // Tenant validation
        Long tenantId = request.getTenantId();

        ResponseEntity<UserTenantResource> tenantResponse = tenantClient.getATenantById(tenantId);
        UserTenantResource tenant = tenantResponse.getBody();

        if (tenant.getName() == null){
            throw new ResourceNotFoundException("Tenant", tenantId);
        }
    }

    private Publication ValidateIfPublicationExists(Review request){
        // Publication validation
        Long publicationId = request.getPublicationId();

        ResponseEntity<Publication> response = publicationClient.getPublication(publicationId);
        Publication publication = response.getBody();

        if (publication == null){
            throw new ResourceNotFoundException("Publication", publicationId);
        }

        return publication;
    }
}
