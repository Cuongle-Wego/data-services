package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private long id;
    private String providerCode;
    private double rating;
    private String title;
    private List<ReviewContent> contents;
    private ReviewAuthor author;
    @JsonProperty("postedAt")
    private String postedAt;
    @JsonProperty("reviewUrl")
    private String reviewUrl;
    private String locale;
} 