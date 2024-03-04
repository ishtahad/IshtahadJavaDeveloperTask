package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportInfoDTO {
    private String airportCode;
    private String airportId;
    private String metrocode;
    private String multicity;

}
