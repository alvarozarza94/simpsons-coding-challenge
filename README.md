# simpsons-coding-challenge

#### Deploy project with docker (On the root of the project "/simpsons-app"):

`$ docker-compose up`

#### Run unitary tests

`$ ./gradlew test`

The coverage of the unit tests is 100% over the code with 32 unitary tests

#### Run integration tests

The integration tests are in a Postman Collection 
For run the tests:

1. Postman->File->Import file
2. Choose file Simpsons.postman_collection.json (root repository)
3. Run this collection (play symbol on the collection)
4. Choose the number of iterations
5. See the results

**Interesting endpoints:**

`User Interface` : <http://localhost:8083/>

`Swagger Interface` : <http://localhost:8083/swagger-ui.html#>

**Considerations:**

Spring Boot 2, Java8 and an embedded H2 database have been used for this project. The code has been organized in a layered architecture, we should not use such an architecture for such a simple project, but the idea is to make an archetype that is the basis for a more complicated microservice. I wanted to make a remark regarding the implemented design:
We wanted to differentiate both at the code level and at the API level the character and phrase entities.
On the one hand, all operations related to characters have been performed and their own phrases have been exposed in a set of endpoints, while in the other controller the operations related to the phrases have been isolated

The user interface has been made simpler and doesn't contain all the functionality, It has been done with JQuery and Bootstrap. We can simply do CRUD operations on the Simpsons characters without taking into account the phrases.

The Swagger interface has all the functionality of the application.

Unitary tests have been done with Mockito.

The application has been dockerized, for this we have created a Dockerfile that compiles the application with a Gradle wrapper and runs the application. All this process is orchestrated with docker-compose in case in the future we decide to use a non-embedded database.
