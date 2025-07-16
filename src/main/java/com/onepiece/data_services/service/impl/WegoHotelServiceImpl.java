package com.onepiece.data_services.service.impl;

import com.onepiece.data_services.model.HotelDetails;
import com.onepiece.data_services.service.WegoHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WegoHotelServiceImpl implements WegoHotelService {
    
    private final RestTemplate restTemplate;
    
    @Autowired
    public WegoHotelServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public HotelDetails getHotelDetails(Long hotelId) {
        return getHotelDetails(hotelId, "en");
    }
    
    public HotelDetails getHotelDetails(Long hotelId, String locale) {
        try {
            String url = "https://srv.wego.com/hotels/hotels/{hotelId}?locale={locale}";
            
            return restTemplate.getForObject(url, HotelDetails.class, hotelId, locale);
        } catch (RestClientException e) {
            // Log the error and return null or empty response
            System.err.println("Error fetching hotel details for hotel ID " + hotelId + ": " + e.getMessage());
            return null;
        }
    }
} 