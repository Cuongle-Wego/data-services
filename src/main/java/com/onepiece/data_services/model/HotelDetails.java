package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDetails {
    
    @JsonProperty("id")
    private Long id;  // return to client
    
    @JsonProperty("builtYear")
    private String builtYear;  // return to client
    
    @JsonProperty("checkIn")
    private String checkIn;  // return to client
    
    @JsonProperty("checkOut")
    private String checkOut;  // return to client
    
    @JsonProperty("email")
    private String email;  // return to client
    
    @JsonProperty("fax")
    private String fax;  // return to client
    
    @JsonProperty("floorsCount")
    private Integer floorsCount;  // return to client
    
    @JsonProperty("gmtOffset")
    private String gmtOffset;  // return to client
    
    @JsonProperty("latitude")
    private Double latitude;  // return to client
    
    @JsonProperty("longitude")
    private Double longitude;  // return to client
    
    @JsonProperty("nativeCurrency")
    private String nativeCurrency;  // return to client
    
    @JsonProperty("phone1")
    private String phone1;  // return to client
    
    @JsonProperty("postalCode")
    private String postalCode;  // return to client
    
    @JsonProperty("reservationPhone")
    private String reservationPhone;
    
    @JsonProperty("roomsCount")
    private Integer roomsCount;
    
    @JsonProperty("star")
    private Integer star;
    
    @JsonProperty("timeZone")
    private String timeZone;
    
    @JsonProperty("website")
    private String website;
    
    @JsonProperty("updatedAt")
    private String updatedAt;
    
    @JsonProperty("permalink")
    private String permalink;
    
    @JsonProperty("nameEn")
    private String nameEn;  // return to client
    
    @JsonProperty("disabled")
    private Boolean disabled;
    
    @JsonProperty("reviews")
    private List<BasicReview> reviews;
    
    @JsonProperty("aiReviews")
    private List<AiReview> aiReviews;
    
    @JsonProperty("distanceToCityCentre")
    private Double distanceToCityCentre;
    
    @JsonProperty("distanceToNearestAirport")
    private Double distanceToNearestAirport;
    
    @JsonProperty("nearestAirport")
    private NearestAirport nearestAirport;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("address")
    private String address;  // return to client
    
    @JsonProperty("nearestLandmark")
    private String nearestLandmark;
    
    @JsonProperty("description")
    private String description;  // return to client
    
    @JsonProperty("similarHotels")
    private List<Long> similarHotels;
    
    @JsonProperty("latestProviderReviews")
    private List<LatestProviderReview> latestProviderReviews;
    
    @JsonProperty("images")
    private List<HotelImage> images;
    
    @JsonProperty("amenities")
    private List<HotelAmenity> amenities;
    
    @JsonProperty("badges")
    private List<HotelBadge> badges;
    
    @JsonProperty("propertyType")
    private PropertyType propertyType;
    
    @JsonProperty("city")
    private HotelCity city;  // return to client
    
    @JsonProperty("district")
    private HotelDistrict district;  // return to client
} 