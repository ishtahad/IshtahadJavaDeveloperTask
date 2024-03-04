package com.ishtahad.packiyo.packiyoDeveloperTask.ServiceImpl;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDTOs.WeatherPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.XmlDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class GlobalServiceImpl implements GlobalService {
    private static final Logger logger = LogManager.getLogger(GlobalServiceImpl.class);
    private final FileServerService fileServerService;
    private final HotelService hotelService;
    private final WeatherService weatherService;

    private final XmlService xmlService;

    public GlobalServiceImpl(FileServerService fileServerService, HotelService hotelService, WeatherService weatherService, XmlService xmlService) {
        this.fileServerService = fileServerService;
        this.hotelService = hotelService;
        this.weatherService = weatherService;
        this.xmlService = xmlService;
    }

    @Override
    public List<CsvDataDTO> csvDataDTOList() {
        return fileServerService.csvDataDTOList();
    }

    @Override
    public List<HotelDisplayDTO> getHotelInfoForDisplay(String city) {
        return hotelService.getHotelInfoForDisplay(city);
    }

    @Override
    public List<WeatherDisplayDTO> getWeatherInfoForDisplay(String city) {
        return weatherService.getWeatherInfoForDisplay(city);
    }


    @Override
    public HashMap<String, Object> generateXML(String country, String city) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String xmlString = generateXmlString(country, city);
            fileServerService.uploadXmlToFTP(xmlString, country);
            map.put("status", 1);
            map.put("message", "Successfully Generated And Saved XML in FTP server");
            map.put("body", xmlString);
            return map;
        } catch (Exception e) {
            logger.error("Error While Generating XML For City: " + city);
            logger.error(e.getMessage());
            map.put("status", 0);
            map.put("message", e.getMessage());
            return map;
        }

    }

    @Override
    public HashMap<String, Object> generateXMLForAll() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<CsvDataDTO> dataDTOList = fileServerService.csvDataDTOList();
            for (CsvDataDTO data : dataDTOList) {
                String xmlString = generateXmlString(data.getCountryName(), data.getCityName());
                fileServerService.uploadXmlToFTP(xmlString, data.getCountryName());
                logger.info("SuccessFully Uploaded XML to FTP Server for City: " + data.getCityName());
            }
            map.put("status", 1);
            map.put("message", "Successfully Generated And Saved XML in FTP server");
            return map;
        } catch (Exception e) {
            map.put("status", 0);
            map.put("message", e.getMessage());
            return map;
        }
    }

    @Override
    public String generateXmlString(String country, String city) {
        HotelPrimaryDTO hotelPrimaryDTO = hotelService.searchHotel(city);
        WeatherPrimaryDTO weatherPrimaryDTO = weatherService.searchWeather(city);
        XmlDTO xmlDTO = new XmlDTO();
        xmlDTO.setCountry(country);
        if (hotelPrimaryDTO != null) {
            xmlDTO.setHotel(hotelPrimaryDTO);
        }
        if (weatherPrimaryDTO != null) {
            xmlDTO.setWeather(weatherPrimaryDTO);
        }
        return xmlService.convertToXml(xmlDTO);
    }


}
