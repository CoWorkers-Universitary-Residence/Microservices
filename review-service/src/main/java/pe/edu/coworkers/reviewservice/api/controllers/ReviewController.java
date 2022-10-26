package pe.edu.coworkers.reviewservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.coworkers.reviewservice.domain.model.entity.Review;
import pe.edu.coworkers.reviewservice.domain.service.ReviewService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(){
        List<Review> reviews;
        reviews = reviewService.getAll();
        if (reviews.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Review> getReview(@PathVariable("id") Long id){
        Review review = reviewService.getById(id);
        if (review == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(review);
    }

    //@GetMapping(value = "/publications/{publicationId}")
    //public ResponseEntity<List<Review>> getReviewByPublicationId(@PathVariable("publicationId") Long id){
    //    List<Review> reviews = reviewService.getByPublicationId(id);
    //    if (reviews.isEmpty()){
    //        return ResponseEntity.noContent().build();
    //    }
    //    return ResponseEntity.ok(reviews);
    //}

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review reviewCreate = reviewService.create(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody Review review){
        review.setId(id);
        Review review1 = reviewService.update(id, review);
        if (review1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(review1);
    }

    @DeleteMapping("{reviewId}")
    public Optional<ResponseEntity<?>> deleteReview(@PathVariable Long reviewId){
        return Optional.ofNullable(reviewService.delete(reviewId));
    }
}
