package com.onepiece.data_services.controller;

import com.onepiece.data_services.model.HotelResponse;
import com.onepiece.data_services.model.HotelInfo;
import com.onepiece.data_services.model.HotelDetails;
import com.onepiece.data_services.model.ReviewsResponse;
import com.onepiece.data_services.service.ReviewService;
import com.onepiece.data_services.service.WegoHotelService;
import com.onepiece.data_services.mapper.HotelMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    
    private final ReviewService reviewService;
    private final WegoHotelService wegoHotelService;
    
    @Autowired
    public HotelController(ReviewService reviewService, WegoHotelService wegoHotelService) {
        this.reviewService = reviewService;
        this.wegoHotelService = wegoHotelService;
    }

    @GetMapping("/hotel/{hotel_id}")
    public HotelResponse getHotelData(@PathVariable Integer hotel_id) {
        // Fetch real reviews from external API
        List<String> reviews = reviewService.getHotelReviews(hotel_id);

        // Fetch real hotel details from external API
        HotelDetails hotelDetails = wegoHotelService.getHotelDetails(hotel_id.longValue());

        // Create HotelInfo from real hotel details using MapStruct mapper
        HotelInfo hotelInfo = hotelDetails != null ? HotelMapper.INSTANCE.toHotelInfo(hotelDetails) : new HotelInfo();


        return new HotelResponse(reviews, hotelInfo);
    }
    
}