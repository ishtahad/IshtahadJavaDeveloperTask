package com.ishtahad.packiyo.packiyoDeveloperTask.ServiceImpl;

import com.ishtahad.packiyo.packiyoDeveloperTask.DTO.XmlDTO;
import com.ishtahad.packiyo.packiyoDeveloperTask.Services.XmlService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.StringWriter;

@Service
public class XmlServiceIml implements XmlService {
    private static final Logger logger = LogManager.getLogger(XmlServiceIml.class);
    @Override
    public String convertToXml(XmlDTO dto) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(dto, writer);
            String xmlString = writer.toString();
            logger.info("SuccessFully Obtained XML String for Country: " + dto.getCountry());
            return xmlString;
        } catch (Exception e) {
            logger.error("Error Generating XML String For Country: " + dto.getCountry());
            logger.error(e.getMessage());
            return null;
        }
    }
}
