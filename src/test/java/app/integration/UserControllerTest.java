package app.integration;

import app.User;
import app.UserDto;
import app.UserService;
import app.exceptions.UserNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                + "/" + postgres.getDatabaseName());
        registry.add("spring.r2dbc.username", postgres::getUsername);
        registry.add("spring.r2dbc.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserService service;

    //INITIALISE DEFAULT VARIABLES
    private User defaultUser;
    private final UserDto defaultUserDto = new UserDto();

    @BeforeEach
    public void init() {
        defaultUserDto.setFirst_name("First Name");
        defaultUserDto.setLast_name("Last Name");
        defaultUserDto.setEmail("email@email.com");
        defaultUserDto.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUserDto.setAddress("Address 1");
        defaultUserDto.setPhone_number("Phone Number");

        defaultUser = service.save(defaultUserDto).block();
    }

    @Test
    @DisplayName("Junit test for getting all users from database")
    void getUsers() {
        User[] users = template.getForObject("/users", User[].class);

        assertNotNull(users);
        assertTrue(users.length > 0);
        assertEquals(users.length, service.getAll().count().block());
    }

    @Test
    @DisplayName("Junit test for getting user by id from database")
    void getById() {
        User user = template.getForObject("/users/" + defaultUser.getId(), User.class);

        assertNotNull(user);
        assertEquals(defaultUser.getId(), user.getId());
        assertEquals(defaultUser.getFirst_name(), user.getFirst_name());
        assertEquals(defaultUser.getLast_name(), user.getLast_name());
    }

    @Test
    @DisplayName("Junit test for save user into database")
    void getByBirthdayRange() {

        LocalDate from = LocalDate.of(1989, 1, 1);
        LocalDate to = LocalDate.of(1991, 1, 1);
        String url = String.format("/users/birthday/range?from=%s&to=%s", from, to);

        User[] users = template.getForObject(url, User[].class);

        assertNotNull(users);
        assertTrue(users[0].getBirthday().isAfter(from) && users[0].getBirthday().isBefore(to));
    }

    @Test
    @DisplayName("Junit test for deleting user by id from database")
    void create() {

        ResponseEntity<User> response = template.exchange("/users", HttpMethod.POST, new HttpEntity<>(defaultUserDto), User.class);
        User user = response.getBody();

        assertNotNull(user);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(defaultUserDto.getFirst_name(), user.getFirst_name());
        assertEquals(defaultUserDto.getLast_name(), user.getLast_name());
        assertEquals(service.getById(user.getId()).block(), user);
    }

    @Test
    @DisplayName("Junit test for updating user by id from database")
    void update() {
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setFirst_name("Updated First Name");
        updatedUserDto.setLast_name("Updated Last Name");

        ResponseEntity<User> response = template.exchange("/users/" +  defaultUser.getId(), HttpMethod.PUT, new HttpEntity<>(updatedUserDto), User.class);
        User user = response.getBody();

        assertNotNull(user);
        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(updatedUserDto.getFirst_name(), user.getFirst_name());
        assertEquals(updatedUserDto.getLast_name(), user.getLast_name());
        assertEquals(service.getById(user.getId()).block(), user);
    }

    @Test
    @DisplayName("Junit test for getting users by birthday range from database")
    void delete() {
        ResponseEntity<Void> response = template.exchange("/users/" + defaultUser.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThrows(UserNotFoundException.class, () -> service.getById(defaultUser.getId()).block());

    }



}
















