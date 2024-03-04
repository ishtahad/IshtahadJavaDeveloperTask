package com.ishtahad.packiyo.packiyoDeveloperTask.Services;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;

import java.util.List;

public interface FileServerService {
    List<CsvDataDTO> csvDataDTOList();
    void uploadXmlToFTP(String xmlString,String country);

}
