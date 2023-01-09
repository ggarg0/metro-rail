Metro Rail App
This application simulates the operations for a Metro rail booking. Once successfully up and running it provides the APIs for

1. Get the card balance for user /metro/api/balance
2. Book a ticket and get journey details for user /metro/api/journey-details
3. Get all the card holder details /metro/api/cardholder-inventory
4. Get all stations footfall count /metro/api/station-footfall

Description
This project uses following features to achieve the desired functionality.

1. Spring Based Exceptional Handling
2. Swagger 3.0 Open API
3. Javax Validation
4. Internally Uses H2 database for simulating Run time DB.
5. JUnit 5 Based test Cases.
6. Jacoco Code Coverage
7. bcrypt for Encryption. [For one way PIN encryption]

Getting Started
Perform the following steps to run this on local

Dependencies
Following dependencies must be installed.

1. Maven
2. Java 11

Installing
To run this repo on local

1. clone this repo
$ git clone https://github.com/ggarg0/metro-rail

2. Once cloned, go inside the metro-rail directory and execute
$ mvn clean install
This will build the project

3. Once project is built, Copy the property file
[https://github.com/ggarg0/metro-rail/blob/main/src/main/resources/application.properties] in a
temp folder and modify the below property to put the logs at your system desired folder
logging.file.path= path_to_generate_log_file
example:
logging.file.path= /Users/Work/metro-rail/logs
this should be a valid path.

4. to execute the project, provide the following command.
$ java -jar ~/metro-rail/target/metro-rail-0.0.1-SNAPSHOT.jar --spring.config.location=file:///Users/...../application.properties

5. Once the application is up and running, open chrome and put below url in the address bar.
http://localhost:8888/metro-rail-api.html
This is swagger API docs.

6. You can try the APIs directly from there or use below curl command to test the application

   Book a ticket and get journey details for user 

  $ curl -X 'POST'
'http://localhost:8888/metro/api/journey-details'
-H 'accept: application/json'
-H 'Content-Type: application/json'
-d '{ "cardNumber": "11111", "pin": "1234", "stationIn": "A1", "stationOut": "A6", "message": "" }'

   Get all the card holder details

  curl -X 'GET'
'http://localhost:8080/metro/api/cardholder-inventory'
-H 'accept: application/json'

   Get the card balance for user

   curl -X 'POST'
'http://localhost:8080/metro/api/balance'
-H 'accept: application/json'
-H 'Content-Type: application/json'
-d '{ "cardNumber": "11111", "pin": "1234" }'

   Get all stations footfall count

  curl -X 'GET'
'http://localhost:8080//metro/api/station-footfall'
-H 'accept: application/json'



Code Coverage
This project uses Junit5, mockito and Jacoco to provide the unit testing. Unit tests are extensively done for service module only as of now. To view the code coverage, use the following commands

$ mvn clean test

or

$ mvn clean install site --offline

Code coverage reports can be found at.

* ~/metro-rail/target/site/jacoco/index.html 
* ~/metro-rail/target/site/jacoco/com.demo.metrorail.service.business.carddetails/CardDetailsServiceImpl.html 
* ~/metro-rail/target/site/jacoco/com.demo.metrorail.service.business.journeydetails/JourneyDetailsServiceImpl.html

Authors
Contributor names and contact info Gaurav Garg [ggarg.bhilai@gmail.com]

Version History
* 1.0 - Initial Release

License
This project is licensed under the MIT License - see the LICENSE.md file for details
