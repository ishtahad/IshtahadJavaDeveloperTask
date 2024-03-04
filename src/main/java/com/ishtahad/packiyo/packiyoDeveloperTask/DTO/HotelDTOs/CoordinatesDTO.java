package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesDTO {
    private String lat;
    @JsonProperty("long")
    private String lon;

}
