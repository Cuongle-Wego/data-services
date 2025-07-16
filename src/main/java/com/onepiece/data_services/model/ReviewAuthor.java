package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAuthor {
    private String name;
    private String type;
    @JsonProperty("typeEn")
    private String typeEn;
    private Country country;
} 