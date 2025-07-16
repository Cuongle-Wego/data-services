package com.onepiece.data_services.service;

import com.onepiece.data_services.model.HotelDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

public interface WegoHotelService {
    public HotelDetails getHotelDetails(Long hotelId);
}