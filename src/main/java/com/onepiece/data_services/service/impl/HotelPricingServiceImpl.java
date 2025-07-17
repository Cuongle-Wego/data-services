package com.onepiece.data_services.service.impl;

import com.onepiece.data_services.model.*;
import com.onepiece.data_services.service.HotelPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelPricingServiceImpl implements HotelPricingService {
    
    private final RestTemplate restTemplate;
    private final CacheManager cacheManager;
    
    // Default values
    private static final long DEFAULT_MAX_WAIT_TIME_MS = 30000; // 30 seconds
    private static final long DEFAULT_POLLING_INTERVAL_MS = 5000; // 5 seconds
    private static final String BASE_SEARCH_URL = "https://srv.wego.com/v3/metasearch/hotels/{hotelId}/searches";
    private static final String BASE_RATES_URL = "https://srv.wego.com/v3/metasearch/hotels/{hotelId}/rates";
    private static final String CLIENT_ID = "1d183258-59e8-4b53-afe0-5378f5c36777";
    
    @Autowired
    public HotelPricingServiceImpl(RestTemplate restTemplate, CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
    }
    
    @Override
    public PricingRatesResponse getHotelPricing(HotelPricingRequest request) {
        String cacheKey = buildCacheKey(request);
        Cache cache = cacheManager.getCache("hotelPricing");
        
        if (cache != null) {
            Cache.ValueWrapper cachedValue = cache.get(cacheKey);
            if (cachedValue != null) {
                // Cache hit - return immediately
                return (PricingRatesResponse) cachedValue.get();
            }
        }
        
        // Cache miss - trigger async population and return null
        populateCacheAsync(request, cacheKey);
        return null;
    }
    
    @Override
    public PricingRatesResponse getHotelPricing(HotelPricingRequest request, long maxWaitTimeMs, long pollingIntervalMs) {
        try {
            // Step 1: Initiate search
            String searchId = initiateSearch(request);
            if (searchId == null) {
                System.err.println("Failed to initiate hotel pricing search");
                return null;
            }
            
            // Step 2: Poll for results
            return pollForPricingResults(request.getHotelId(), searchId, request.getLocale(), 
                                       request.getCurrencyCode(), maxWaitTimeMs, pollingIntervalMs);
            
        } catch (Exception e) {
            System.err.println("Error getting hotel pricing: " + e.getMessage());
            return null;
        }
    }
    
    private String initiateSearch(HotelPricingRequest request) {
        try {
            // Build the search request
            PricingSearchRequest searchRequest = buildSearchRequest(request);
            
            // Set up headers similar to the curl example
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("accept", "*/*");
            headers.set("accept-language", "en-US,en;q=0.9");
            headers.set("origin", "https://www.wego.com");
            headers.set("referer", "https://www.wego.com/");
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36");
            headers.set("x-wego-client-id", CLIENT_ID);
            headers.set("x-wego-session-id", UUID.randomUUID().toString());
            
            HttpEntity<PricingSearchRequest> entity = new HttpEntity<>(searchRequest, headers);
            
            // Make the POST request to initiate search
            String url = BASE_SEARCH_URL + "?locale=" + getDefaultValue(request.getLocale(), "en") + 
                        "&currencyCode=" + getDefaultValue(request.getCurrencyCode(), "USD") + 
                        "&amountType=NIGHTLY&clientId=" + CLIENT_ID;
            
            ResponseEntity<PricingSearchResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, PricingSearchResponse.class, request.getHotelId());
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                PricingSearchResponse searchResponse = response.getBody();
                if (searchResponse.getSearch() != null) {
                    return searchResponse.getSearch().getId();
                }
            }
            
            return null;
            
        } catch (RestClientException e) {
            System.err.println("Error initiating pricing search: " + e.getMessage());
            return null;
        }
    }
    
    private PricingRatesResponse pollForPricingResults(Integer hotelId, String searchId, String locale, 
                                                     String currencyCode, long maxWaitTimeMs, long pollingIntervalMs) {
        long startTime = System.currentTimeMillis();
        
        while (System.currentTimeMillis() - startTime < maxWaitTimeMs) {
            try {
                // Set up headers
                HttpHeaders headers = new HttpHeaders();
                headers.set("accept", "*/*");
                headers.set("accept-language", "en-US,en;q=0.9");
                headers.set("content-type", "application/json");
                headers.set("origin", "https://www.wego.com");
                headers.set("referer", "https://www.wego.com/");
                headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36");
                headers.set("x-wego-client-id", CLIENT_ID);
                headers.set("x-wego-session-id", UUID.randomUUID().toString());
                
                HttpEntity<String> entity = new HttpEntity<>(headers);
                
                // Build the polling URL
                String url = BASE_RATES_URL + "?searchId=" + searchId + 
                           "&locale=" + getDefaultValue(locale, "en") + 
                           "&currencyCode=" + getDefaultValue(currencyCode, "VND") + 
                           "&amountType=NIGHTLY&clientId=" + CLIENT_ID + 
                           "&output=ALL_RATES&similarHotelLimit=10";
                
                // Make the GET request to poll for results
                ResponseEntity<PricingRatesResponse.ApiResponseDTO> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, PricingRatesResponse.ApiResponseDTO.class, hotelId);
                
                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    PricingRatesResponse.ApiResponseDTO apiResponse = response.getBody();
                    
                    // Check if we have rates data (indicating search is complete)
                    if (apiResponse.getRates() != null && !apiResponse.getRates().isEmpty()) {
                        // Convert the nested API response to our flat response model
                        return apiResponse.toFlatResponse();
                    }
                }
                
                // Wait before next poll
                Thread.sleep(pollingIntervalMs);
                
            } catch (RestClientException e) {
                System.err.println("Error polling for pricing results: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Polling interrupted: " + e.getMessage());
                break;
            }
        }
        
        System.err.println("Timeout reached while polling for pricing results");
        return null;
    }
    
    private PricingSearchRequest buildSearchRequest(HotelPricingRequest request) {
        PricingSearchRequest searchRequest = new PricingSearchRequest();
        
        // Build search parameters
        PricingSearchRequest.SearchParams searchParams = new PricingSearchRequest.SearchParams();
        searchParams.setSiteCode(getDefaultValue(request.getSiteCode(), "US"));
        searchParams.setLocale(getDefaultValue(request.getLocale(), "en"));
        searchParams.setCurrencyCode(getDefaultValue(request.getCurrencyCode(), "VND"));
        searchParams.setCheckIn(request.getCheckIn());
        searchParams.setCheckOut(request.getCheckOut());
        searchParams.setHotelId(request.getHotelId());
        searchParams.setAppType("WEB_APP");
        searchParams.setDeviceType("DESKTOP");
        searchParams.setClientCreatedAt(Instant.now().toString());
        searchParams.setUserLoggedIn(false);
        
        // Handle rooms - use default if not provided
        List<PricingSearchRequest.RoomConfig> rooms = new ArrayList<>();
        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            for (HotelPricingRequest.RoomRequest roomRequest : request.getRooms()) {
                PricingSearchRequest.RoomConfig roomConfig = new PricingSearchRequest.RoomConfig();
                roomConfig.setAdultsCount(roomRequest.getAdultsCount() != null ? roomRequest.getAdultsCount() : 2);
                roomConfig.setChildrenCount(roomRequest.getChildrenCount() != null ? roomRequest.getChildrenCount() : 0);
                roomConfig.setChildrenAges(roomRequest.getChildrenAges() != null ? roomRequest.getChildrenAges() : new ArrayList<>());
                rooms.add(roomConfig);
            }
        } else {
            // Default room configuration
            PricingSearchRequest.RoomConfig defaultRoom = new PricingSearchRequest.RoomConfig();
            defaultRoom.setAdultsCount(2);
            defaultRoom.setChildrenCount(0);
            defaultRoom.setChildrenAges(new ArrayList<>());
            rooms.add(defaultRoom);
        }
        searchParams.setRooms(rooms);
        
        searchRequest.setSearch(searchParams);
        
        // Similar hotels configuration
        PricingSearchRequest.SimilarHotelsConfig similarHotels = new PricingSearchRequest.SimilarHotelsConfig();
        similarHotels.setLimit(10);
        searchRequest.setSimilarHotels(similarHotels);
        
        searchRequest.setIncludeDirect(true);
        
        return searchRequest;
    }
    
    @Async("taskExecutor")
    public void populateCacheAsync(HotelPricingRequest request, String cacheKey) {
        try {
            System.out.println("Starting async cache population for key: " + cacheKey);
            PricingRatesResponse result = getHotelPricing(request, DEFAULT_MAX_WAIT_TIME_MS, DEFAULT_POLLING_INTERVAL_MS);
            
            if (result != null) {
                Cache cache = cacheManager.getCache("hotelPricing");
                if (cache != null) {
                    cache.put(cacheKey, result);
                    System.out.println("Successfully cached pricing result for key: " + cacheKey);
                }
            } else {
                System.err.println("Failed to get pricing result for async cache population: " + cacheKey);
            }
        } catch (Exception e) {
            System.err.println("Error in async cache population for key " + cacheKey + ": " + e.getMessage());
        }
    }
    
    private String buildCacheKey(HotelPricingRequest request) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(request.getHotelId()).append("_");
        keyBuilder.append(request.getCheckIn()).append("_");
        keyBuilder.append(request.getCheckOut()).append("_");
        keyBuilder.append(getDefaultValue(request.getLocale(), "en")).append("_");
        keyBuilder.append(getDefaultValue(request.getCurrencyCode(), "USD")).append("_");
        keyBuilder.append(getDefaultValue(request.getSiteCode(), "US")).append("_");
        
        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            keyBuilder.append(request.getRooms().toString());
        } else {
            keyBuilder.append("default");
        }
        
        return keyBuilder.toString();
    }
    
    private String getDefaultValue(String value, String defaultValue) {
        return value != null && !value.trim().isEmpty() ? value : defaultValue;
    }
} 