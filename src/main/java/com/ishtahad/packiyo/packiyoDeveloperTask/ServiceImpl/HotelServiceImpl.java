package com.ishtahad.packiyo.packiyoDeveloperTask.ServiceImpl;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.HotelPrimaryDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDTOs.SR;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.RoutingServices.HotelApiCallService;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.HotelService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelApiCallService hotelApiCallService;

    public HotelServiceImpl(HotelApiCallService hotelApiCallService) {
        this.hotelApiCallService = hotelApiCallService;
    }

    @Override
    public HotelPrimaryDTO searchHotel(String cityName){
        HotelPrimaryDTO hotelPrimaryDTO = hotelApiCallService.callApi(cityName);
        if(hotelPrimaryDTO == null ) return null;
        List<SR> srList = hotelPrimaryDTO.getSr().stream()
                .filter(sr -> sr.getType().equals("HOTEL"))
                .limit(3)
                .collect(Collectors.toList());
        hotelPrimaryDTO.setSr(srList);
        return hotelPrimaryDTO;
    }

    @Override
    public List<HotelDisplayDTO> getHotelInfoForDisplay(String city){
        List<HotelDisplayDTO> hotelDisplayDTOList= new ArrayList<>();
        HotelPrimaryDTO hotelPrimaryDTO = searchHotel(city);
        if(hotelPrimaryDTO == null) return hotelDisplayDTOList;
        for(SR sr : hotelPrimaryDTO.getSr()){
                HotelDisplayDTO hotelDisplayDTO = new HotelDisplayDTO();
                hotelDisplayDTO.setCityName(hotelPrimaryDTO.getQ());
                hotelDisplayDTO.setRegionDisplayName(sr.getRegionNames() != null ? sr.getRegionNames().getDisplayName(): "");
                hotelDisplayDTO.setProvince(sr.getHotelAddress() != null ? sr.getHotelAddress().getProvince(): "");
                hotelDisplayDTO.setCityName(sr.getHotelAddress() != null ? sr.getHotelAddress().getCity(): "");
                hotelDisplayDTO.setStreet(sr.getHotelAddress() != null ? sr.getHotelAddress().getStreet():"");
                hotelDisplayDTO.setLat(sr.getCoordinates() != null ? sr.getCoordinates().getLat():"");
                hotelDisplayDTO.setLon(sr.getCoordinates() != null ? sr.getCoordinates().getLon():"");
                hotelDisplayDTO.setShortName(sr.getRegionNames() != null ? sr.getRegionNames().getShortName(): "");
                hotelDisplayDTO.setIsoCode3(sr.getHierarchyInfo() != null ? sr.getHierarchyInfo().getCountry()!= null ? sr.getHierarchyInfo().getCountry().getIsoCode3():"" :"");
                hotelDisplayDTOList.add(hotelDisplayDTO);
        }
        return hotelDisplayDTOList;
    }
}
