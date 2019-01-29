# Integration testing
Example Spring Boot application to illustrate integration testing.

![IT](https://www.guru99.com/images/3-2016/032816_1230_SystemInteg1.png)

## Requirements
- Java 8
- Apache Maven 3+
- Docker (or SQL Server database)

## Usage
- Compile application with command `mvn install` (Unit and Integration tests will be run)
- Run the command `docker-compose up` to start up SQL Server database
- With your favourite database connection tool execute script found in "src/main/resources/batch/schema-sqlserver.sql"
- Start up application running command `mvn spring-boot:run`
- Call to "http://localhost:8070/api/v1/hello" or "http://localhost:8070/api/v1/version" endpoints to collect request information to be logged request.log file
- Execute batch process by calling "http://localhost:8070/api/v1/launch-batch" endpoint
- See the log information processed and inserted into database
