package com.ishtahad.packiyo.packiyoDeveloperTask.Controllers;

import com.ishtahad.packiyo.packiyoDeveloperTask.Services.GlobalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api")
public class RestController {
    private final GlobalService globalService;

      public RestController(GlobalService globalService) {
        this.globalService = globalService;
    }

       @GetMapping("generate-xml-for-all")
       public ResponseEntity<HashMap<String,Object>> generateXMLForAll(){
           HashMap<String,Object> map = globalService.generateXMLForAll();
           if(map.get("status").equals(1)){
               return new ResponseEntity<>(map, HttpStatus.OK);
           }else{
               return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
}
