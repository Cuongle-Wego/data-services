package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelImage {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("primary")
    private Boolean primary;
    
    @JsonProperty("url")
    private String url;
    
    @JsonProperty("description")
    private String description;
} 