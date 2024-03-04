package com.ishtahad.packiyo.packiyoDeveloperTask.RoutingServices;


import com.ishtahad.packiyo.packiyoDeveloperTask.Services.GlobalService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class CamelRoutingService extends RouteBuilder {
    private static final Logger logger = LogManager.getLogger(CamelRoutingService.class);

    private final GlobalService globalService;


    private final CamelContext camelContext;

    public CamelRoutingService(GlobalService globalService, CamelContext camelContext) {
        this.globalService = globalService;
        this.camelContext = camelContext;
    }

    @Value("${file_server.userName}")
    private String username;

    @Value("${file_server.password}")
    private String password;

    @Value("${file_server.hostName}")
    private String hostname;

    @Value("${file_server.fileName}")
    private String fileName;


    @Override
    public void configure() throws Exception {

        long currentTimestamp = System.currentTimeMillis();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentTimestamp);
        String dynamicFileName = "${header.country}" + "_" + timestamp + ".xml";
        onException(org.apache.camel.component.file.GenericFileOperationFailedException.class)
                .handled(true)
                .log("Error occurred during Reading or writing From FTP Server: ${exception.message}")
                .maximumRedeliveries(4)
                .redeliveryDelay(2000);

        from("timer://myTimer?period=21600000")
                .log("CSV Data Read and Processing Start from FTP Server....")
                .pollEnrich().simple("ftp://" + username + ":" + password + "@" + hostname + "/?fileName=" + fileName)
                .convertBodyTo(String.class)
                .process(exchange -> {
                    String[] lines = exchange.getIn().getBody(String.class).split("\\r?\\n");
                    for (int i = 1; i < lines.length; i++) {
                        String line = lines[i];
                        String country = line.split(",")[0];
                        String city = line.split(",")[1];
                        logger.info("Country : " + country +" City: " + city);
                        String xmlString = globalService.generateXmlString(country, city);
                        ProducerTemplate template = camelContext.createProducerTemplate();
                        template.sendBodyAndHeader("direct:uploadXmlFileToFtp", xmlString, "country", country);
                    }
                })
                .log("Successfully Generated XML And stored in FTP Server.")
                .end();


        from("direct:ftpToCSVRoute")
                .pollEnrich().simple("ftp://" + username + ":" + password + "@" + hostname + "/?fileName=" + fileName)
                .log("Reading CSV file: ${file:name}")
                .convertBodyTo(String.class);


        from("direct:uploadXmlFileToFtp")
                .setHeader("CamelFileName", simple(dynamicFileName))
                .to("ftp://" + username + ":" + password + "@" + hostname + "/");
    }
}
