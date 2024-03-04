package com.ishtahad.packiyo.packiyoDeveloperTask.Services;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;

import java.util.List;

public interface HotelService {
     HotelPrimaryDTO searchHotel(String cityName);
     List<HotelDisplayDTO> getHotelInfoForDisplay(String city);
}
