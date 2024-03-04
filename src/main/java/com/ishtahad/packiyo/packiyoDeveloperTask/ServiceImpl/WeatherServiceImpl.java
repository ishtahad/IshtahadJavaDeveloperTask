package com.ishtahad.packiyo.packiyoDeveloperTask.ServiceImpl;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs.WeatherPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.RoutingServices.WeatherApiCallService;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.WeatherService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final WeatherApiCallService weatherApiCallService;

    public WeatherServiceImpl(WeatherApiCallService weatherApiCallService) {
        this.weatherApiCallService = weatherApiCallService;
    }

    @Override
    public WeatherPrimaryDTO searchWeather(String city) {
        return weatherApiCallService.callApi(city);
    }

    @Override
    public List<WeatherDisplayDTO> getWeatherInfoForDisplay(String city){
        List<WeatherDisplayDTO> weatherDisplayDTOList = new ArrayList<>();
        WeatherPrimaryDTO weatherPrimaryDTO = searchWeather(city);
        if(weatherPrimaryDTO == null) return null;
        WeatherDisplayDTO weatherDisplayDTO = new WeatherDisplayDTO();
        weatherDisplayDTO.setCityName(weatherPrimaryDTO.getName());
        weatherDisplayDTO.setLon(weatherPrimaryDTO.getCoord() != null ? weatherPrimaryDTO.getCoord().getLon():0);
        weatherDisplayDTO.setLat(weatherPrimaryDTO.getCoord() != null ? weatherPrimaryDTO.getCoord().getLat():0);
        weatherDisplayDTO.setTemp(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getTemp():0);
        weatherDisplayDTO.setHumidity(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getHumidity():0);
        weatherDisplayDTO.setPressure(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getPressure():0);
        weatherDisplayDTO.setFeels_like(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getFeels_like():0);
        weatherDisplayDTO.setTemp_max(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getTemp_max():0);
        weatherDisplayDTO.setTemp_min(weatherPrimaryDTO.getMain() != null ? weatherPrimaryDTO.getMain().getTemp_min():0);
        weatherDisplayDTO.setTimeZone(weatherDisplayDTO.getTimeZone());
        weatherDisplayDTO.setVisibility(weatherDisplayDTO.getVisibility());
        weatherDisplayDTO.setWindDeg(weatherPrimaryDTO.getWind() != null ? weatherPrimaryDTO.getWind().getDeg():0);
        weatherDisplayDTO.setWindSpeed(weatherPrimaryDTO.getWind() != null ? weatherPrimaryDTO.getWind().getSpeed():0);
        weatherDisplayDTOList.add(weatherDisplayDTO);
        return weatherDisplayDTOList;
    }
}
