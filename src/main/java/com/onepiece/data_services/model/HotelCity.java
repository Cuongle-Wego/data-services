package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelCity {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("enName")
    private String enName;
    
    @JsonProperty("permalink")
    private String permalink;
    
    @JsonProperty("countryCode")
    private String countryCode;
    
    @JsonProperty("countryName")
    private String countryName;
    
    @JsonProperty("countryPermalink")
    private String countryPermalink;
} 