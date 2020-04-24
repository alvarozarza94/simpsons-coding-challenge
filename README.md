# simpsons-coding-challenge

#### Deploy project with docker (On the root of the project "/simpsons-app"):

`$ docker-compose up`


**Interesting endpoints:**

`User Interface` : <http://localhost:8083/>

`Swagger Interface` : <http://localhost:8083/swagger-ui.html#>

**Considerations:**

Spring Boot 2, Java8 and an embedded H2 database have been used for this project. The code has been organized in a layered architecture, we should not use such an architecture for such a simple project, but the idea is to make an archetype that is the basis for a more complicated microservice.

The user interface has been made simpler and doesn't contain all the functionality, It has been done with JQuery and Bootstrap. We can simply do CRUD operations on the Simpsons characters without taking into account the phrases.

The Swagger interface has all the functionality of the application.

Unitary tests have been done with Mockito.

The application has been dockerized, for this we have created a Dockerfile that compiles the application with a Gradle wrapper and runs the application. All this process is orchestrated with docker-compose in case in the future we decide to use a non-embedded database.
