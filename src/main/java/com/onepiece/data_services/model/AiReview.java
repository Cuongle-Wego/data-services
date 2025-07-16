package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiReview {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("sentiment")
    private String sentiment;
    
    @JsonProperty("percentage")
    private Double percentage;
    
    @JsonProperty("hotelId")
    private Long hotelId;
    
    @JsonProperty("createdAt")
    private String createdAt;
    
    @JsonProperty("updatedAt")
    private String updatedAt;
} 