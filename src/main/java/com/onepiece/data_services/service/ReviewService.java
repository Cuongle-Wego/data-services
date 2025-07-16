package com.onepiece.data_services.service;

import com.onepiece.data_services.model.ReviewsResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

public interface ReviewService {
    
    public List<String> getHotelReviews(Integer hotelId);
} 