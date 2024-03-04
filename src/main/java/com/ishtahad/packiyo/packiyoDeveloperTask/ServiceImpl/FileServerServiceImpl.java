package com.ishtahad.packiyo.packiyoDeveloperTask.ServiceImpl;
import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.CsvDataDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.FileServerService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServerServiceImpl implements FileServerService {
    private static final Logger logger = LogManager.getLogger(FileServerServiceImpl.class);
    private final ProducerTemplate producerTemplate;
    private final CamelContext camelContext;

    public FileServerServiceImpl(ProducerTemplate producerTemplate, CamelContext camelContext) {
        this.producerTemplate = producerTemplate;
        this.camelContext = camelContext;
    }

    @Override
    public List<CsvDataDTO> csvDataDTOList() {
        List<CsvDataDTO> csvDataDTOList = new ArrayList<>();
        try {
            String processedData =  producerTemplate.requestBody("direct:ftpToCSVRoute", null, String.class);
            csvDataDTOList = getStringToDTOS(processedData);
            return csvDataDTOList;
        } catch (Exception e) {
            logger.error("Error Occurred While Trying to Connect to FTP Server and Reading Data ");
            logger.error(e.getMessage());
            return csvDataDTOList;
        }
    }
    @Override
    public void uploadXmlToFTP(String xmlString,String country) {
        try{
            ProducerTemplate template = camelContext.createProducerTemplate();
            template.sendBodyAndHeader("direct:uploadXmlFileToFtp", xmlString,"country",country);
            logger.info("SuccessFully Uploaded XML to FTP Server for City: " + country);
        }catch(Exception e){
            logger.error("Error Occurred While Trying to Upload XML to FTP For Country: " + country);
            logger.error(e.getMessage());
        }

    }

    private static List<CsvDataDTO> getStringToDTOS(String processedData) {
        try{
            List<CsvDataDTO> csvDataDTOList = new ArrayList<>();
            String[] lines = processedData.split("\\r?\\n");
            for (String line : lines) {
                String[] fields = line.split(",");
                CsvDataDTO dto = new CsvDataDTO();
                dto.setCountryName(fields[0]);
                dto.setCityName(fields[1]);
                dto.setDate(fields[2]);
                csvDataDTOList.add(dto);
            }
            csvDataDTOList.removeFirst();
            return csvDataDTOList;
        }catch(Exception e){
            logger.error("Error While Converting String to DTO'S");
            logger.error(e.getMessage());
            return null;
        }

    }
}
