# CLEAR SOLUTION TEST TASK

## Brief description

The project is written in Hexagonal Architecture in Multi-Module format, where each module is a separate layer in the architecture with its own dependencies.
The project is fully reactive using Spring WebFlux, R2DBC, ReactorTests.
The project implements FlywayMogration, as well as Docker-based containerization to run the application on any machine.
The project implements JUnit tests using ReactorTests, as well as Integration tests using Testcontainers and SpringWeb.
The project also uses the following technologies: PostgreSQL, Lombok, MapStruct, HibernateValidation, SpringWeb, etc.

## Startup Instructions

### Run Docker Compose

Run the `docker-compose.yml` file with two containers, a database, and a project. To do this, execute the following command in the terminal within the project folder:

Windows:
```bash
docker-compose up --build -d
```

Linux:
```bash
sudo docker-compose up --build -d
```

## Testing

### Run Unit tests

To run the unit tests, you need to run this command in the terminal in the folder with the prefix.

```bash
./gradlew clean unitTests --info
```

### Run Integration tests

To run the integration tests, you need to run this command in the terminal in the folder with the prefix.

```bash
./gradlew clean integrationTests --info
```

### Testing endpoints

To test Rest endpoints through Postman, you need to launch a docker container with the project and open a workspace with tests in Postman.

Link for tests: [Postman](https://www.postman.com/grey-star-845470/workspace/clearsolutiontask/collection/28421538-20a466bf-df9e-407f-bb9e-5d55a0fd0cbf?action=share&creator=28421538)
