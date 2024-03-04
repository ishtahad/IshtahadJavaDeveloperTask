package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionNamesDTO {
    private String fullName;
    private String shortName;
    private String displayName;
    private String primaryDisplayName;
    private String secondaryDisplayName;
    private String lastSearchName;

}
