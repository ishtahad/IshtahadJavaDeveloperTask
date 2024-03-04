package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordDTO {
    private double lon;
    private double lat;

}
