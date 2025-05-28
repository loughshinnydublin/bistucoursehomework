package com.kob.backend.service.impl.review;


import com.kob.backend.mapper.ReviewMapper;
import com.kob.backend.pojo.Review;
import com.kob.backend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Review> getReviewsByProductId(BigInteger productId) {
        return reviewMapper.findByProductId(productId);
    }

    @Override
    public Map<String, String> addReview(Review review) {
        Map<String, String> map = new HashMap<>();
        try {
            reviewMapper.insert(review);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }
} 