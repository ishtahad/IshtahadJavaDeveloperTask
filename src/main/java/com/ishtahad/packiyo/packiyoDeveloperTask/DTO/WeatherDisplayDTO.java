package com.ishtahad.packiyo.packiyoDeveloperTask.DTO;

import lombok.Data;

@Data
public class WeatherDisplayDTO {
     private String cityName;
     private int timeZone;
     private double windSpeed;
     private int windDeg;
     private int visibility;
     private double temp;
     private double feels_like;
     private double temp_min;
     private double temp_max;
     private int pressure;
     private int humidity;
     private double lon;
     private double lat;
}
