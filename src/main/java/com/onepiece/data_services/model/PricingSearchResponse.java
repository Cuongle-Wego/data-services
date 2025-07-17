package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingSearchResponse {
    
    @JsonProperty("search")
    private SearchInfo search;
    
    @JsonProperty("score")
    private Double score;
    
    @JsonProperty("rates")
    private List<Object> rates; // Initially empty
    
    @JsonProperty("rooms")
    private Object rooms;
    
    @JsonProperty("rateAmenities")
    private Object rateAmenities;
    
    @JsonProperty("similarHotelsRateAmenities")
    private Object similarHotelsRateAmenities;
    
    @JsonProperty("orderedRateIdsByBasePrice")
    private List<String> orderedRateIdsByBasePrice;
    
    @JsonProperty("similarHotels")
    private List<Object> similarHotels;
    
    @JsonProperty("similarHotelProviders")
    private List<Object> similarHotelProviders;
    
    @JsonProperty("groupSearch")
    private Boolean groupSearch;
    
    @JsonProperty("recommendation")
    private Object recommendation;
    
    @JsonProperty("priceDisplaying")
    private Object priceDisplaying;
    
    @JsonProperty("roomTypes")
    private List<Object> roomTypes;
    
    @JsonProperty("newRateIds")
    private List<String> newRateIds;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchInfo {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("searchType")
        private String searchType;
        
        @JsonProperty("hotel")
        private HotelBasicInfo hotel;
        
        @JsonProperty("district")
        private DistrictInfo district;
        
        @JsonProperty("city")
        private CityInfo city;
        
        @JsonProperty("worldRegion")
        private RegionInfo worldRegion;
        
        @JsonProperty("country")
        private CountryInfo country;
        
        @JsonProperty("user")
        private Object user;
        
        @JsonProperty("roomsCount")
        private Integer roomsCount;
        
        @JsonProperty("guestsCount")
        private Integer guestsCount;
        
        @JsonProperty("checkIn")
        private String checkIn;
        
        @JsonProperty("checkOut")
        private String checkOut;
        
        @JsonProperty("createdAt")
        private String createdAt;
        
        @JsonProperty("locale")
        private String locale;
        
        @JsonProperty("siteCode")
        private String siteCode;
        
        @JsonProperty("currencyCode")
        private String currencyCode;
        
        @JsonProperty("deviceType")
        private String deviceType;
        
        @JsonProperty("appType")
        private String appType;
        
        @JsonProperty("userCountryCode")
        private String userCountryCode;
        
        @JsonProperty("userGeoSubdivisionCode")
        private String userGeoSubdivisionCode;
        
        @JsonProperty("key")
        private String key;
        
        @JsonProperty("cityCode")
        private String cityCode;
        
        @JsonProperty("cityName")
        private String cityName;
        
        @JsonProperty("countryCode")
        private String countryCode;
        
        @JsonProperty("countryName")
        private String countryName;
        
        @JsonProperty("crossSell")
        private Boolean crossSell;
        
        @JsonProperty("scoreTesting")
        private Object scoreTesting;
        
        @JsonProperty("bowOnlyTesting")
        private Object bowOnlyTesting;
        
        @JsonProperty("removePartnerRateTesting")
        private Object removePartnerRateTesting;
        
        @JsonProperty("rooms")
        private List<Object> rooms;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HotelBasicInfo {
        @JsonProperty("id")
        private Integer id;
        
        @JsonProperty("name")
        private String name;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DistrictInfo {
        @JsonProperty("id")
        private Integer id;
        
        @JsonProperty("name")
        private String name;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CityInfo {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("name")
        private String name;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionInfo {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("name")
        private String name;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CountryInfo {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("name")
        private String name;
    }
} 