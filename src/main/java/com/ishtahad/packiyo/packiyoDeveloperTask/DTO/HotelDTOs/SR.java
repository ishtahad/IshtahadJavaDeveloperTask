package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SR {
    @JsonProperty("@type")
    private String typeA;
    private String index;
    private String gaiaId;
    private String hotelId;
    private String type;
    private RegionNamesDTO regionNames;
    private EssIdDTO essId;
    private CoordinatesDTO coordinates;
    private HierarchyInfoDTO hierarchyInfo;
    private String cityId;
    private HotelAddressDTO hotelAddress;

}
