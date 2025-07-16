package com.onepiece.data_services.mapper;

import com.onepiece.data_services.model.HotelDetails;
import com.onepiece.data_services.model.HotelInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {
    
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    
    HotelInfo toHotelInfo(HotelDetails source);
    
}