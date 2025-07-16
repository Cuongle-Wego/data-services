package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelAmenity {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("priority")
    private Integer priority;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("nameAll")
    private String nameAll;
} 