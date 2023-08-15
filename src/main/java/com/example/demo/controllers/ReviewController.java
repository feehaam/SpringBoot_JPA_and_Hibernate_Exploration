package com.example.demo.controllers;

import com.example.demo.entities.Review;
import com.example.demo.iservices.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> get(@PathVariable int reviewId) {
        Optional<Review> optionalReview = reviewService.getReviewById(reviewId);
        return optionalReview.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> get() {
        List<Review> listOfReviews = reviewService.getAllReviews();
        if (listOfReviews == null || listOfReviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no reviews");
        }
        return ResponseEntity.ok(listOfReviews);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Review> add(@RequestBody Review review, @PathVariable int productId) {
        return ResponseEntity.ok(reviewService.createReview(productId, review));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> update(@PathVariable int reviewId, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(reviewId, review);
        return updatedReview != null ? ResponseEntity.ok(updatedReview) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable int reviewId) {
        if (reviewService.deleteReview(reviewId)) {
            return ResponseEntity.ok("Review is deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete review");
        }
    }
}
