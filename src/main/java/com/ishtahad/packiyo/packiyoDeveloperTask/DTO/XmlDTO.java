package com.ishtahad.packiyo.packiyoDeveloperTask.DTO;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs.WeatherPrimaryDTO;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement
@Data
public class XmlDTO {
    private String country;
    private HotelPrimaryDTO hotel;
    private WeatherPrimaryDTO weather;


}
