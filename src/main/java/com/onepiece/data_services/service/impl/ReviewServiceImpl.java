package com.onepiece.data_services.service.impl;

import com.onepiece.data_services.model.ReviewsResponse;
import com.onepiece.data_services.service.ReviewService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final RestTemplate restTemplate;
    
    @Autowired
    public ReviewServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ReviewsResponse getHotelReviews(Integer hotelId, Integer perPage, Integer page) {
        try {
            String baseUrl = "https://srv.wego.com/hotels/v2/hotels/{hotelId}/reviews/search";
            String url = baseUrl + "?per_page={perPage}&page={page}&sorts[]=posted_at,desc&sorts[]=rating,desc";
            
            return restTemplate.getForObject(url, ReviewsResponse.class, hotelId, perPage, page);
        } catch (RestClientException e) {
            // Log the error and return null or empty response
            System.err.println("Error fetching hotel reviews: " + e.getMessage());
            return null;
        }
    }
    
    public List<String> getHotelReviews(Integer hotelId) {
        ReviewsResponse reviewsResponse = getHotelReviews(hotelId, 100, 1);
        List<String> reviews = new ArrayList<>();

        if (reviewsResponse != null && reviewsResponse.getData() != null) {
            // Extract review comments from the API response
            reviews = reviewsResponse.getData().stream()
                .flatMap(review -> review.getContents().stream())
                .map(content -> content.getComment())
                .filter(comment -> comment != null && !comment.trim().isEmpty())
                .toList();
        }
        return reviews;
    }
} 