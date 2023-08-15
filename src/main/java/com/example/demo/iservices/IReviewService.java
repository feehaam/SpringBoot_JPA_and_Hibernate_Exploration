package com.example.demo.iservices;

import com.example.demo.entities.Review;
import java.util.List;
import java.util.Optional;

public interface IReviewService {
	Review createReview(int productId, Review review);
    Optional<Review> getReviewById(int reviewId);
    List<Review> getAllReviews();
    Review updateReview(int reviewId, Review updatedReview);
    boolean deleteReview(int reviewId);
}
