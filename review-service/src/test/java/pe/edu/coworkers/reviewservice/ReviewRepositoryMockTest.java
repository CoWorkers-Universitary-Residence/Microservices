package pe.edu.coworkers.reviewservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.coworkers.reviewservice.api.client.PublicationClient;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;
import pe.edu.coworkers.reviewservice.domain.persistence.ReviewRepository;
import pe.edu.coworkers.reviewservice.domain.service.ReviewService;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ReviewRepositoryMockTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PublicationClient publicationClient;

    @Test
    public void whenFindByPublicationIdThenListReview(){
        Long publicationId = 1L;

        Review review = Review.builder()
                .date(new Date())
                .comment("New comment")
                .score(5)
                .publicationId(publicationId)
                .tenantId(1L)
                .build();

        reviewService.create(review);

        List<Review> founds = reviewRepository.findAllByPublicationId(publicationId);
        Assertions.assertThat(founds.size()).isEqualTo(15);
    }

    @Test void whenNewReviewIsCreatedThenPublicationScoreUpdates(){
        Long publicationId = 1L;
        int newScore = 5;

        Review review = Review.builder()
                .date(new Date())
                .comment("New comment")
                .score(newScore)
                .publicationId(publicationId)
                .tenantId(1L)
                .build();

        reviewService.create(review);

        Publication publication = publicationClient.getPublication(publicationId).getBody();
        Assertions.assertThat(publication.getScore()).isEqualTo(newScore);
    }
}
