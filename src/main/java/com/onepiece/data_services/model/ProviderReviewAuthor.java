package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderReviewAuthor {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("typeEn")
    private String typeEn;
    
    @JsonProperty("country")
    private Country country;
} 