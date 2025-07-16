package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelBadge {
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("subtext")
    private String subtext;
} 