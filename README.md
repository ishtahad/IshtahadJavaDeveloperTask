# IshtahadJavaDeveloperTask
 A Spring Boot Application with Apache Camel to read csv file from FTP Server, get responses from external APIs, combine responses from APIs, convert them into XML files and upload those in FTP Server via Apache Camel.

Technologies Used:
1. Spring Boot,
2. Java
3. Apache Camel
4. Thymeleaf
5. log4j

Required Tasks:

1st part - Automated:
1. Connect to an FTP server
2. Get the data from a CSV which will contain Countries, Cities, Dates.
3. Connect to at least two of the Given API's:
4. Combine the data by Country and store in separate XMLs on the FTP
5. There should be an option to run the automated process at a specific time of day or an option to run it at specific intervals e.g., every 6 hours.

2nd part - Manual:
1. Make a simple UI with a drop-down selection for Country, input field for City and a date picker for Date and a Submit button to get and display the data for the selected input.
2. When data is shown, add an Export button that will generate and save the data to the FTP in the same XML format as in the automated process.
* No configurable data should be hardcoded instead it should be kept in a separate config file.
* Errors from the API should be logged. FTP credentials should be set in a config file.

My Approach:

1st part - Automated:

Approach 1:

When the project starts a camel route is created. It makes a connection to the FTP server, reads the csv file to process it and converts it in a Array List. Data from the Array List is then used to call 2 APIs and responses from those are stored in FTP Server in XML files with country name and time stamp. This camel route is called after a fixed time of 6 hours.

Approach 2:
User can hit the Rest API: http://localhost:8080/api/generate-xml-for-all . It will do all the above-mentioned tasks after hitting the API.

* Logger information is saved in to a log file in logs folder of the project named application.log.
* All the configuration properties are defined in application.properties.

2nd part - Manual:

User hits the URL : http://localhost:8080/

A UI appears prompting user to select country from drop down, write city in a text box and select Date from date picker. After selecting required fields which is country and city, user needs to click on the button submit. Then a selection of data is shown to user and user can click on export button. If successful then user is prompt to a new screen where the generated XML file can be seen.

Home Page (http://localhost:8080/)

<img src="https://github.com/ishtahad/IshtahadJavaDeveloperTask/blob/main/images/ManualApproach1.PNG">

Display Page. When Submit Button is clicked and successful data retrieval.

<img src="https://github.com/ishtahad/IshtahadJavaDeveloperTask/blob/main/images/ManualApproach2.PNG">

Success Page. When successful XML convertion achieved.
<img src="https://github.com/ishtahad/IshtahadJavaDeveloperTask/blob/main/images/ManualApproach3.PNG">

Please make sure to configure properties from application.properties.