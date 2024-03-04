package com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherPrimaryDTO {
    private CoordDTO coord;
    private ArrayList<WeatherDTO> weather;
    private String base;
    private MainDTO main;
    private int visibility;
    private WindDTO wind;
    private CloudsDTO clouds;
    private int dt;
    private SysDTO sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
