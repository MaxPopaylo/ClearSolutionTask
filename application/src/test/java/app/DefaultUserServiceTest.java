package app;

import app.exceptions.UserNotFoundException;
import app.ports.UserDtoMapper;
import app.ports.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;


import static org.mockito.Mockito.*;

@Tag("unit")
class DefaultUserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserDtoMapper mapper;

    @InjectMocks
    private DefaultUserService service;

    private final User defaultUser = new User();
    private final UserDto defaultUserDto = new UserDto();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void init() {
        defaultUser.setId(1);
        defaultUser.setFirst_name("First Name");
        defaultUser.setLast_name("Last Name");
        defaultUser.setEmail("email@email.com");
        defaultUser.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUser.setAddress("Address 1");
        defaultUser.setPhone_number("Phone Number");

        defaultUserDto.setFirst_name("First Name");
        defaultUserDto.setLast_name("Last Name");
        defaultUserDto.setEmail("email@email.com");
        defaultUserDto.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUserDto.setAddress("Address 1");
        defaultUserDto.setPhone_number("Phone Number");
    }

    @Test
    @DisplayName("Junit test for getting all users from database")
    void getAll() {
        when(repository.findAll()).thenReturn(Flux.just(defaultUser, defaultUser));

        StepVerifier.create(service.getAll())
                .expectNext(defaultUser)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Junit test for getting user by id from database")
    void getById() {
        when(repository.findById(1)).thenReturn(Mono.just(defaultUser));

        StepVerifier.create(service.getById(1))
                .expectNext(defaultUser)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Junit test for save user into database")
    void save() {
        when(repository.save(defaultUser)).thenReturn(Mono.just(defaultUser));
        when(mapper.toUser(defaultUserDto)).thenReturn(defaultUser);

        StepVerifier.create(service.save(defaultUserDto))
                .expectNext(defaultUser)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(defaultUser);
    }

    @Test
    @DisplayName("Junit test for deleting user by id from database")
    void delete() {
        when(repository.findById(1)).thenReturn(Mono.just(defaultUser));
        when(repository.delete(defaultUser)).thenReturn(Mono.empty());

        StepVerifier.create(service.delete(1))
                .expectComplete()
                .verify();

        when(repository.findById(1)).thenReturn(Mono.empty());

        StepVerifier
                .create(service.getById(1))
                .expectError(UserNotFoundException.class)
                .verify();

        verify(repository, times(1)).delete(defaultUser);
    }

    @Test
    @DisplayName("Junit test for updating user by id from database")
    void update() {

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setFirst_name("Updated First Name");
        updatedUserDto.setLast_name("Updated Last Name");

        User updatedUser = defaultUser;
        updatedUser.setFirst_name(updatedUserDto.getFirst_name());
        updatedUser.setLast_name(updatedUserDto.getLast_name());

        when(repository.findById(1)).thenReturn(Mono.just(defaultUser));
        when(repository.save(updatedUser)).thenReturn(Mono.just(updatedUser));

        StepVerifier.create(service.update(1, updatedUserDto))
                .expectNext(updatedUser)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(updatedUser);
    }

    @Test
    @DisplayName("Junit test for getting users by birthday range from database")
    void getByBirthdayRange() {

        LocalDate from = LocalDate.of(1989, 1, 1);
        LocalDate to = LocalDate.of(1991, 1, 1);

        when(repository.findByBirthdayRange(from, to)).thenReturn(Flux.just(defaultUser ,defaultUser));

        StepVerifier.create(service.getByBirthdayRange(from, to))
                .expectNext(defaultUser)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findByBirthdayRange(from, to);
    }
}