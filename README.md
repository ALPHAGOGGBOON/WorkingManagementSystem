## Prerequisites
The list of software or dependencies to operate the program
- Java 17 and above
- Mysql
- Maven
- Any Ide
/////////////////////////////////////////////////////////////////////
The list of maven pom dependencies
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- mysql-connector-j
- lombok
- spring-boot-starter-test
- springdoc-openapi-starter-webmvc-ui
- hibernate-validator
- hazelcast
- spring-boot-starter-cache
- spring-security-test
/////////////////////////////////////////////////////////////////////
The .properties file setting
spring.datasource.url = jdbc:mysql://localhost:3306/your_database?useSSL=false
spring.datasource.username = TheRoot
spring.datasource.password = @password123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
/////////////////////////////////////////////////////////////////////

## How to operate
The easy and basic method to run and and operate the code such as:
- run the java application code under the main folder
  - For this project the java file WorkingManagementSystemApplication.java is the main file
- any browser (google chrome suggested)
  - go to localhost with 8080 default port and link to run and visualize the function
  - for better visualization swagger or postman will be used
    - swagger: http://localhost:8080/swagger-ui/index.html#/
    - download and install postman online
   
## Current structure
user & admin --> api --> coresponding service

## Current authentication method
using InMemoryUserDetailsManager, which just hardcode the authentication

## project setup
To install and setup a stable version go to "https://start.spring.io/" to set up is suggested
