# Flux Spring Boot Example #

With the new Spring 5 and Spring Boot 2, there is now a new feature calling FluxWeb. It allows non blocking requests between services on server or frontend side.

This is a Example how I anderstand it and have implemented in a solution.

## Common ##

This Project uses Swagger 2 UI to allow some calls to test the block and non blocking situation. There is also implemented Flayway to run when the Spring Boot application run. (Provider project only)

## Provider ##

This is the data provider and is connecting to a H2 database. It also has a wait implemented to simulate a huge service work. It can by configured in the application.properties.

## Consumer ##

This will consume the data from provider. To simulate the block or non blocking calls.
