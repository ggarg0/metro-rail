<h2>Metro Rail App</h2>
This application simulates the operations for a Metro rail booking. Once successfully up and running it provides the APIs for <br><br>

1. Get the card balance for user <b>/metro/api/balance</b>
2. Book a ticket and get journey details for user <b>/metro/api/journey-details</b>
3. Get all the card holder details <b>/metro/api/cardholder-inventory</b>
4. Get all stations footfall count <b>/metro/api/station-footfall</b>

<h2>Description</h2>
This project uses following features to achieve the desired functionality.
<br><br>
<OL>
<LI>Spring Based Exceptional Handling</LI>
<LI>Swagger 3.0 Open API</LI>
<LI>Javax Validation</LI>
<LI>Internally Uses H2 database for simulating Run time DB</LI>
<LI>JUnit 5 Based test Cases</LI>
<LI>Jacoco Code Coverage</LI>
<LI>bcrypt for Encryption. [For one way PIN encryption]</LI>
</OL>

<h2>Getting Started</h2>

Perform the following steps to run this on local

<h3>Dependencies</h3>
Following dependencies must be installed.

<OL><br>
<LI>Maven</LI>
<LI>Java 11</LI>
</OL>

<h3>Installing</h3>
To run this repo on local
<OL><br>
<LI>clone this repo 
<b>$ git clone https://github.com/ggarg0/metro-rail</b></LI> <br>

<LI>Once cloned, go inside the metro-rail directory and execute <br>
<b>$ mvn clean install</b><br>
This will build the project</LI> <br>

<LI>Once project is built, Copy the property file
[https://github.com/ggarg0/metro-rail/blob/main/src/main/resources/application.properties] in a
temp folder and modify the below property to put the logs at your system desired folder <br>
<b>logging.file.path= path_to_generate_log_file</b><br>
example:<br>
<b>logging.file.path= /Users/Work/metro-rail/logs</b><br>
this should be a valid path.</LI><br>

<LI>to execute the project, provide the following command.
<b>$ java -jar ~/metro-rail/target/metro-rail-0.0.1-SNAPSHOT.jar --spring.config.location=file:///Users/...../application.properties</b></LI><br>

<LI>Once the application is up and running, open chrome and put below url in the address bar.<br>
<a href="http://localhost:8888/metro-rail-api.html">http://localhost:8888/metro-rail-api.html</a><br>
This is swagger API docs.</LI><br>

<LI>You can try the APIs directly from there or use below curl command to test the application

  <b>Book a ticket and get journey details for user </b>

 curl -X 'POST'<br>
'http://localhost:8888/metro/api/journey-details'<br>
-H 'accept: application/json'<br>
-H 'Content-Type: application/json'<br>
-d '{ "cardNumber": "11111", "pin": "1234", "stationIn": "A1", "stationOut": "A6", "message": "" }'<br>

  <b>Get all the card holder details</b>

 curl -X 'GET'<br>
'http://localhost:8080/metro/api/cardholder-inventory'<br>
-H 'accept: application/json'<br>

  <b>Get the card balance for user</b>

 curl -X 'POST'<br>
'http://localhost:8080/metro/api/balance'<br>
-H 'accept: application/json'<br>
-H 'Content-Type: application/json'<br>
-d '{ "cardNumber": "11111", "pin": "1234" }'<br>

  <b>Get all stations footfall count</b>

 curl -X 'GET'<br>
'http://localhost:8080//metro/api/station-footfall'<br>
-H 'accept: application/json'<br>
</LI>
</OL>
<h2>Code Coverage</h2>
This project uses Junit5, mockito and Jacoco to provide the unit testing. Unit tests are extensively done for service module only as of now. To view the code coverage, use the following commands<br><br>

<b>$ mvn clean test</b><br>
or<br>
<b>$ mvn clean install site --offline</b>

Code coverage reports can be found at.

* ~/metro-rail/target/site/jacoco/index.html 
* ~/metro-rail/target/site/jacoco/com.demo.metrorail.service.business.carddetails/CardDetailsServiceImpl.html 
* ~/metro-rail/target/site/jacoco/com.demo.metrorail.service.business.journeydetails/JourneyDetailsServiceImpl.html

<h2>Authors</h2>
Contributor names and contact info Gaurav Garg [ggarg.bhilai@gmail.com]

<h2>Version History</h2>
<UL>
<LI>1.0 - Initial Release</LI>
</UL>
<h2>License</h2>

This project is licensed under the MIT License - see the LICENSE.md file for details
