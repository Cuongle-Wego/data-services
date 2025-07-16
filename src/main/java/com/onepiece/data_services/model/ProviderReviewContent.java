package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderReviewContent {
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("comment")
    private String comment;
} 