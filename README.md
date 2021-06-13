# typeqast-zadatak

Meter Readings
This is Typeqast techincal task application that was developed using Java 11 and Spring Boot 2.5.0. It provides APIs for retrieving Meter Readings as well as inserting Meter Readings. Application uses H2 in memory database for data persistence and Swagger UI

Running application
Application can be started using command:

mvn spring-boot:run
Running tests
Application tests can be started using command:

mvn test
Database
When application is started, access to database is available via link:

http://localhost:8080/h2-console
Credentials for accessing database can be found in:

application.properties

Swagger
Application APIs can be overviewed and tested by using Swagger. Each endpoint is described along with parameters it uses. Once application is started, Swagger UI is available on link:

 http://localhost:8080/swagger-ui.html
