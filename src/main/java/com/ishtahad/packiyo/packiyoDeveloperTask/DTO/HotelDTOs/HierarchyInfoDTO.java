package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HierarchyInfoDTO {
    private CountryInfoDTO country;
    private AirportInfoDTO airport;

}
