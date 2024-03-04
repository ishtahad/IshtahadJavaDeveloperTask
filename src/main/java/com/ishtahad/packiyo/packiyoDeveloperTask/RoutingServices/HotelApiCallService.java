package com.ishtahad.packiyo.packiyoDeveloperTask.RoutingServices;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Service
public class HotelApiCallService {
    private static final Logger logger = LogManager.getLogger(HotelApiCallService.class);
    private final RestTemplate restTemplate;

    @Value("${hotel_api.url}")
    private String baseUrl;

    @Value("${hotel_api.key}")
    private String apiKey;

    @Value("${hotel_api.host}")
    private String apiHost;


    public HotelApiCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HotelPrimaryDTO callApi(String cityName){
        cityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
        String url = baseUrl + cityName + "&locale=en_US";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        RequestEntity<Object> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        try{
            ResponseEntity<HotelPrimaryDTO> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, HotelPrimaryDTO.class);
            HotelPrimaryDTO hotelPrimaryDTO = response.getBody();
            if(hotelPrimaryDTO != null && hotelPrimaryDTO.getRc().equals("GOOGLE_AUTOCOMPLETE")){
                logger.error("No Data Found, Hotel API Call For City : " + cityName);
                return null;
            }
            logger.info("SuccessFully Obtained Data Of Hotel for City: " + cityName);
            return response.getBody();
        }catch(Exception e){
            logger.error("Error During Hotel API Call For City : " + cityName);
            logger.error(e.getMessage());
            return null;
        }
    }

}
