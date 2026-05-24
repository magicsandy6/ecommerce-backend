package com.ecommerce.controller;

import com.ecommerce.entity.Review;
import com.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/product/{productId}/user/{userId}")
    public ResponseEntity<Review> addReview(@PathVariable Long productId, @PathVariable Long userId, @RequestBody Review review) {
        Review savedReview = reviewService.addReview(productId, userId, review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(review -> new ResponseEntity<>(review, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        Review updatedReview = reviewService.updateReview(id, reviewDetails);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<Map<String, Double>> getAverageRating(@PathVariable Long productId) {
        Double averageRating = reviewService.getAverageRating(productId);
        Map<String, Double> response = new HashMap<>();
        response.put("averageRating", averageRating);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
