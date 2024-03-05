package com.ishtahad.packiyo.packiyoDeveloperTask.RoutingServices;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs.WeatherPrimaryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Service
public class WeatherApiCallService {
    private static final Logger logger = LogManager.getLogger(WeatherApiCallService.class);
    private final RestTemplate restTemplate;

    @Value("${weather_api.url}")
    private String baseUrlWeather;

    @Value("${weather_api.key}")
    private String apiKeyWeather;


    public WeatherApiCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public WeatherPrimaryDTO callApi(String cityName){
        String cityNameOriginal = cityName;
        try{
            cityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
            String url = baseUrlWeather + cityName + "&APPID=" + apiKeyWeather ;
            ResponseEntity<WeatherPrimaryDTO> response = restTemplate.getForEntity(url, WeatherPrimaryDTO.class);
            logger.info("SuccessFully Obtained Data Of Weather for City: " + cityNameOriginal);
            return response.getBody();
        } catch (Exception ex){
            logger.error("Error During Weather API Call For City : " + cityNameOriginal);
            logger.error(ex.getMessage());
            return null;
        }

    }
}
