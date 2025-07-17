package com.onepiece.data_services.service;

import com.onepiece.data_services.model.HotelPricingRequest;
import com.onepiece.data_services.model.PricingRatesResponse;

public interface HotelPricingService {
    
    /**
     * Get hotel pricing by initiating a search and polling for results
     * @param request the pricing request with hotel details and date range
     * @return the pricing response with rates and room information
     */
    PricingRatesResponse getHotelPricing(HotelPricingRequest request);
    
    /**
     * Get hotel pricing with custom timeout and polling interval
     * @param request the pricing request with hotel details and date range
     * @param maxWaitTimeMs maximum time to wait for results in milliseconds
     * @param pollingIntervalMs interval between polling attempts in milliseconds
     * @return the pricing response with rates and room information
     */
    PricingRatesResponse getHotelPricing(HotelPricingRequest request, long maxWaitTimeMs, long pollingIntervalMs);
} 