package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingSearchRequest {
    
    @JsonProperty("search")
    private SearchParams search;
    
    @JsonProperty("similarHotels")
    private SimilarHotelsConfig similarHotels;
    
    @JsonProperty("includeDirect")
    private Boolean includeDirect;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchParams {
        @JsonProperty("siteCode")
        private String siteCode;
        
        @JsonProperty("locale")
        private String locale;
        
        @JsonProperty("currencyCode")
        private String currencyCode;
        
        @JsonProperty("checkIn")
        private String checkIn;
        
        @JsonProperty("checkOut")
        private String checkOut;
        
        @JsonProperty("rooms")
        private List<RoomConfig> rooms;
        
        @JsonProperty("hotelId")
        private Integer hotelId;
        
        @JsonProperty("appType")
        private String appType;
        
        @JsonProperty("deviceType")
        private String deviceType;
        
        @JsonProperty("clientCreatedAt")
        private String clientCreatedAt;
        
        @JsonProperty("userLoggedIn")
        private Boolean userLoggedIn;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomConfig {
        @JsonProperty("adultsCount")
        private Integer adultsCount;
        
        @JsonProperty("childrenCount")
        private Integer childrenCount;
        
        @JsonProperty("childrenAges")
        private List<Integer> childrenAges;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimilarHotelsConfig {
        @JsonProperty("limit")
        private Integer limit;
    }
} 