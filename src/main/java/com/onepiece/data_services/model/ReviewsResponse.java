package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsResponse {
    private List<Review> data;
    private Meta meta;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private Pagination pagination;
        @JsonProperty("hightlights")
        private List<String> highlights;
        @JsonProperty("synonymsMap")
        private Map<String, List<String>> synonymsMap;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {
        @JsonProperty("currentPage")
        private int currentPage;
        @JsonProperty("nextPage")
        private Integer nextPage;
        @JsonProperty("prevPage")
        private Integer prevPage;
        @JsonProperty("totalCount")
        private int totalCount;
        @JsonProperty("totalPages")
        private int totalPages;
    }
} 