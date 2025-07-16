package com.onepiece.data_services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {
    private List<String> reviews;
    private HotelInfo hotel_info;
}