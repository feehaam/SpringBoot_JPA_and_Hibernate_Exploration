package com.example.demo.services;

import com.example.demo.dao.ReviewDAO;
import com.example.demo.entities.Product;
import com.example.demo.entities.Review;
import com.example.demo.iservices.IProductService;
import com.example.demo.iservices.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewDAO reviewDao;
    @Autowired 
    private IProductService products;

    @Override
    public Review createReview(int productId, Review review) {
    	Product product = products.getProductById(productId).get();
    	if(product != null) {
    		product.addReview(review);
    		products.updateProduct(productId, product);
    		return review;
    	}
    	return null;
    }

    @Override
    public Optional<Review> getReviewById(int reviewId) {
        return reviewDao.findById(reviewId);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    @Override
    public Review updateReview(int reviewId, Review updatedReview) {
        Optional<Review> existingReview = getReviewById(reviewId);
        if (existingReview.isPresent()) {
            updatedReview.setId(reviewId);
            reviewDao.save(updatedReview);
            return updatedReview;
        }
        return null;
    }

    @Override
    public boolean deleteReview(int reviewId) {
        if (reviewDao.existsById(reviewId)) {
            reviewDao.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
