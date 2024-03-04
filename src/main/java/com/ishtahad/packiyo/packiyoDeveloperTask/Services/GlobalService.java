package com.ishtahad.packiyo.packiyoDeveloperTask.Services;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDisplayDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

public interface GlobalService {
       List<CsvDataDTO> csvDataDTOList();
       List<HotelDisplayDTO> getHotelInfoForDisplay(String city);
       List<WeatherDisplayDTO> getWeatherInfoForDisplay(String city);
       HashMap<String,Object> generateXML(String country,String city);
       HashMap<String,Object> generateXMLForAll();
       String generateXmlString(String country,String city);
}
