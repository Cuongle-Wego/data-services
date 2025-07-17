package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPricingRequest {
    
    @JsonProperty("hotel_id")
    private Integer hotelId;
    
    @JsonProperty("check_in")
    private String checkIn;
    
    @JsonProperty("check_out")
    private String checkOut;
    
    @JsonProperty("rooms")
    private List<RoomRequest> rooms;
    
    @JsonProperty("locale")
    private String locale;
    
    @JsonProperty("currency_code")
    private String currencyCode;
    
    @JsonProperty("site_code")
    private String siteCode;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomRequest {
        @JsonProperty("adults_count")
        private Integer adultsCount;
        
        @JsonProperty("children_count")
        private Integer childrenCount;
        
        @JsonProperty("children_ages")
        private List<Integer> childrenAges;
    }
} 