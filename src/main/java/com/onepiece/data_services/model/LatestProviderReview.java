package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatestProviderReview {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("providerCode")
    private String providerCode;
    
    @JsonProperty("rating")
    private Double rating;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("contents")
    private List<ProviderReviewContent> contents;
    
    @JsonProperty("author")
    private ProviderReviewAuthor author;
    
    @JsonProperty("postedAt")
    private String postedAt;
    
    @JsonProperty("reviewUrl")
    private String reviewUrl;
    
    @JsonProperty("locale")
    private String locale;
} 