package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingRatesResponse {
    
    @JsonProperty("rates")
    private List<HotelRate> rates;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HotelRate {
        
        @JsonProperty("description")
        private String description;
        
        @JsonProperty("price")
        private Double price;
        
        @JsonProperty("provider")
        private String provider;

    }
    
    // DTO class to handle the nested API response structure
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiResponseDTO {
        
        @JsonProperty("rates")
        private List<ApiHotelRate> rates;
        
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ApiHotelRate {
            
            @JsonProperty("description")
            private String description;
            
            @JsonProperty("price")
            private PriceInfo price;
            
            @JsonProperty("provider")
            private Provider provider;
        }
        
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PriceInfo {
            
            @JsonProperty("totalAmountUsd")
            private Double totalAmountUsd;
        }
        
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Provider {
            
            @JsonProperty("name")
            private String name;
        }
        
        // Method to convert API DTO to flat response model
        public PricingRatesResponse toFlatResponse() {
            if (this.rates == null) {
                return new PricingRatesResponse(null);
            }
            
            List<HotelRate> flatRates = this.rates.stream()
                .map(apiRate -> new HotelRate(
                    apiRate.getDescription(),
                    apiRate.getPrice() != null ? apiRate.getPrice().getTotalAmountUsd() : null,
                    apiRate.getProvider() != null ? apiRate.getProvider().getName() : null
                ))
                .collect(Collectors.toList());
            
            return new PricingRatesResponse(flatRates);
        }
    }
} 