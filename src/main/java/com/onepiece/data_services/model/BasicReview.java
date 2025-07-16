package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicReview {
    
    @JsonProperty("score")
    private Double score;
    
    @JsonProperty("count")
    private Integer count;
    
    @JsonProperty("percentage")
    private Double percentage;
    
    @JsonProperty("reviewerGroup")
    private String reviewerGroup;
} 