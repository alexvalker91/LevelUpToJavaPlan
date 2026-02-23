This document contains homework tasks for `Spring-boot`.



Task 1 - Hello-world application
==========================

**Cost**: 20 points.

* Using https://start.spring.io create a Spring-boot app.
* Create CommandLineRunner and output 'hello world'.
* Start your application.
* Check that spring context is up and there is 'hello world' message in console.


Task 2 - CRUD REST application
==========================

**Cost**: 20 points.

* Create app that should support create, read, update and delete operations for some entity
* Use Spring Data module
* Don't use Spring Data REST starter

Task 3 - CRUD application: security
==========================

**Cost**: 20 points.

* Implement authentication and authorization mechanism
* OAuth2 should be used
* JWT Token should be used


Task 4 (Optional) - CRUD application: externalized configuration
==========================

* Should support different environments - local, dev, stg, prod
* Spring profiles
* Each environment - different db properties


Task 5 - CRUD application: data migrating
==========================

**Cost**: 20 points.

* Add tool for migrating data
* Flyway or Liquibase

Task 6 (Optional) - CRUD application: actuator
==========================

* Enable actuator
* Implement a few custom health indicators
* Implement a few  custom metrics using Prometheus

Task 7 - CRUD application: testing
==========================

**Cost**: 20 points.

* In memory db must be used for testing purpose 
* Implement repository testing
* Implement unit tests
* Implement tests for RestController using mock mvc
* Implement integration tests
