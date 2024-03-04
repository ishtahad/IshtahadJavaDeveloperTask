package com.ishtahad.packiyo.packiyoDeveloperTask.Services;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs.WeatherPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDisplayDTO;

import java.util.List;

public interface WeatherService {
    WeatherPrimaryDTO searchWeather(String city);
    List<WeatherDisplayDTO> getWeatherInfoForDisplay(String city);
}
