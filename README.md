# Project service-poller [![Build Status](https://travis-ci.org/stemlaur/service-poller.svg?branch=master)](https://travis-ci.org/stemlaur/service-poller)

## Description
**service-poller** allows clients to register service they wish to monitor.

The service pools periodically specified endpoints representing the registered services
and provide a status about their health.

## Usage
To use the service, please run the following commands:

```bash
> mvn clean install
> java -jar application/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar
```

on a serapate console you can access to the logs by typing:
```bash
> tail -f app.log
```

For now the application registers two services and periodically checks their health statuses
and if it changes, it will display it on screen. The health check are returned randomly.

This usage paragraph will of course change as the project evolves.

## The modules
The modules are splitted into two:

 - a module [domain](./domain/README.md) agnostic of any infrastructure concerns, exposing:
    - the value objects and entities
    - the domain services
    - interfaces for the infrastruture
 - a module [application](./application/README.md) where are gathered 
    - the entry point Main.java 
    - in-memory or fake infrastructure related classes
 
 ## Contribute
 
 - keep the domain free of any infrastructure related details
    - in order to be able to log and/or publish metrics [domain probe](https://martinfowler.com/articles/domain-oriented-observability.html)
     should be used (See `com.livi.servicepoller.domain.ServicePollerInstrumentation` example)
 - the `master` branch is protected, any pull request can be proposed, they will be merge if the CI is green and after a review
 - please update the READMEs as soon as the usage or implementation change
 - if you have any comment or issue, please file either a pull request or you can create an issue
 
 ## Next steps
 
 There are lot of infrastruture details which could be implemented , some of them could be:
 
 - be able to register service using an http endpoint
 - store the services in a database
 - store the service statuses in a time serie
 - observability
 - if a relational database is choosen, use liquibase or flyway to be able to refactor the schema on start-up
 - security of the endpoints
 - ... 