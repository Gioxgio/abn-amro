## Assumptions

The following assumptions were made during the development of this project:

- The generate password length is 7 chars and is letter only

## Shortcuts

The following shortcuts were taken during the development of this project:

- The database url has been overwritten with a java option in the docker-compose file<br>
  **Why do I think this is wrong?** The url in the configuration is silently replaced<br>
  **What I think should be the right solution?** There should be two different configurations:
    - One loaded when the application run locally
    - One loaded when the application run within a container<br>
      The loading of the configuration should be based on two different spring profiles
- Most of the fields in the customer table are nullable<br>
  **Why do I think this is wrong?** The data stored in the database are legally required to open a bank account in most
  of the European countries<br>
  **What I think should be the right solution?** They should be `NOT NULL`. `id_document` should also be `UNIQUE`
- Legal age is hardcoded<br>
  **Why do I think this is wrong?** Even though is common in Europe that people under 18 years old are underage, that
  limit shouldn't be in the code<br>
  **What I think should be the right solution?** The age should be in the configuration file (to avoid to have to
  specify the same number for every country, the default value could be 18)
- Currency is hardcoded<br>
  **Why do I think this is wrong?** Even almost every country in Europe adopted Euro as currency it shouldn't be
  hardcoded<br>
  **What I think should be the right solution?** The currency should be in the configuration file
- Information to generate IBANs are hardcoded<br>
  **Why do I think this is wrong?** It may slow down expansion processes<br>
  **What I think should be the right solution?** The information should be in the configuration file

## Pre requisites

In order to run the application the following software has to be installed:

- [Java 17 or later](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://docs.docker.com/get-docker/)

## Run the project

To start the project, open the project folder in the terminal and run the following command<br>
`sh start_project.sh`

## Swagger

The swagger documentation can be read at http://127.0.0.1:8080/swagger-ui/index.html

## Dependencies

| Dependency      | Purpose                                   | Documentation                              |
|-----------------|-------------------------------------------|--------------------------------------------|
| flyway          | DB migration                              | https://flywaydb.org/                      |
| lombok          | - Logging<br/>- Removing boilerplate code | https://projectlombok.org/                 |
| postgresql      | DB connection driver                      | https://postgresql.org/                    |
| spring data JPA | DB interaction                            | https://spring.io/projects/spring-data-jpa |
| springdoc       | APIs documentation                        | https://springdoc.org/                     |