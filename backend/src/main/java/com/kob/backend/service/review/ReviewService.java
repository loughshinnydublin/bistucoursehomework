package com.kob.backend.service.review;


import com.kob.backend.pojo.Review;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
 
public interface ReviewService {
    List<Review> getReviewsByProductId(BigInteger productId);
    Map<String, String> addReview(Review review);
} 