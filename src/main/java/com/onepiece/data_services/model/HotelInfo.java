package com.onepiece.data_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    @JsonProperty("id")
    private Long id;  // return to client

    @JsonProperty("builtYear")
    private String builtYear;  // return to client

    @JsonProperty("checkIn")
    private String checkIn;  // return to client

    @JsonProperty("checkOut")
    private String checkOut;  // return to client

    @JsonProperty("email")
    private String email;  // return to client

    @JsonProperty("fax")
    private String fax;  // return to client

    @JsonProperty("floorsCount")
    private Integer floorsCount;  // return to client

    @JsonProperty("gmtOffset")
    private String gmtOffset;  // return to client

    @JsonProperty("latitude")
    private Double latitude;  // return to client

    @JsonProperty("longitude")
    private Double longitude;  // return to client

    @JsonProperty("nativeCurrency")
    private String nativeCurrency;  // return to client

    @JsonProperty("phone1")
    private String phone1;  // return to client

    @JsonProperty("postalCode")
    private String postalCode;  // return to client

    @JsonProperty("nameEn")
    private String nameEn;  // return to client

    @JsonProperty("address")
    private String address;  // return to client

    @JsonProperty("description")
    private String description;  // return to client

    @JsonProperty("city")
    private HotelCity city;  // return to client

    @JsonProperty("district")
    private HotelDistrict district;  // return to client
} 