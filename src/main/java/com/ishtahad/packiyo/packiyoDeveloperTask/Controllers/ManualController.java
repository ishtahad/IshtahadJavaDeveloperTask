package com.ishtahad.packiyo.packiyoDeveloperTask.Controllers;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.HotelDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.WeatherDisplayDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.GlobalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;

@Controller
public class ManualController {
    private final GlobalService globalService;

    public ManualController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

    @GetMapping("/csv-data")
    public ResponseEntity<List<CsvDataDTO>> fetchData() {
        List<CsvDataDTO> dataList = globalService.csvDataDTOList();
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/home")
    public String sayHello(Model model){
        List<CsvDataDTO> list = globalService.csvDataDTOList();
        model.addAttribute("inputForm",new CsvDataDTO());
        model.addAttribute("countryList", list);
        return "home";
    }

    @PostMapping("/submit")
    public String submitForm(CsvDataDTO inputForm, Model model) {
        List<HotelDisplayDTO> hotelList = globalService.getHotelInfoForDisplay(inputForm.getCityName());
        List<WeatherDisplayDTO> weatherList = globalService.getWeatherInfoForDisplay(inputForm.getCityName());
        model.addAttribute("inputForm",inputForm);
        model.addAttribute("countryList", inputForm);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("weatherList", weatherList);
        model.addAttribute("city", inputForm.getCityName());
        model.addAttribute("country", inputForm.getCountryName());
        return "home";
    }

    @PostMapping("/export")
    public String export(@RequestParam String country,@RequestParam String city, Model model) {
        HashMap<String,Object> map = globalService.generateXML(country,city);
        model.addAttribute("country",country);
        model.addAttribute("city",city);
        if(map.get("status").equals(1)){
            model.addAttribute("result", map.get("body").toString());
            return "success";
        }
        else return "failed";
    }


}
