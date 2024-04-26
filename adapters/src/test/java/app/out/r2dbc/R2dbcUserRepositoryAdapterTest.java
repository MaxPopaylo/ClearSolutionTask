package app.out.r2dbc;

import app.User;
import app.out.mapper.UserDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@Tag("unit")
class R2dbcUserRepositoryAdapterTest {

    @Mock
    private R2dbcUserRepository repository;
    
    @Mock
    private UserDboMapper mapper;

    @InjectMocks
    private R2dbcUserRepositoryAdapter adapter;
    
    private final User defaultUser = new User();
    private final UserDbo defaultUserDbo = new UserDbo();

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

        defaultUserDbo.setId(1);
        defaultUserDbo.setFirst_name("First Name");
        defaultUserDbo.setLast_name("Last Name");
        defaultUserDbo.setEmail("email@email.com");
        defaultUserDbo.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUserDbo.setAddress("Address 1");
        defaultUserDbo.setPhone_number("Phone Number");
    }

    @Test
    @DisplayName("Junit test for getting all users from database")
    void findAll() {
        when(repository.findAll()).thenReturn(Flux.just(defaultUserDbo, defaultUserDbo));
        when(mapper.toEntity(defaultUserDbo)).thenReturn(defaultUser);

        StepVerifier.create(adapter.findAll())
                .expectNext(defaultUser)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Junit test for getting user by id from database")
    void findById() {
        when(repository.findById(1)).thenReturn(Mono.just(defaultUserDbo));
        when(mapper.toEntity(defaultUserDbo)).thenReturn(defaultUser);

        StepVerifier.create(adapter.findById(1))
                .expectNext(defaultUser)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Junit test for save user into database")
    void save() {
        when(repository.save(defaultUserDbo)).thenReturn(Mono.just(defaultUserDbo));
        when(mapper.toDbo(defaultUser)).thenReturn(defaultUserDbo);
        when(mapper.toEntity(defaultUserDbo)).thenReturn(defaultUser);

        StepVerifier.create(adapter.save(defaultUser))
                .expectNext(defaultUser)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(defaultUserDbo);
    }

    @Test
    @DisplayName("Junit test for deleting user by id from database")
    void delete() {
        when(repository.findById(1)).thenReturn(Mono.just(defaultUserDbo));
        when(repository.delete(defaultUserDbo)).thenReturn(Mono.empty());

        when(mapper.toDbo(defaultUser)).thenReturn(defaultUserDbo);

        StepVerifier.create(adapter.delete(defaultUser))
                .expectComplete()
                .verify();

        verify(repository, times(1)).delete(defaultUserDbo);
    }

    @Test
    @DisplayName("Junit test for getting users by birthday range from database")
    void findByBirthdayRange() {
        LocalDate from = LocalDate.of(1989, 1, 1);
        LocalDate to = LocalDate.of(1991, 1, 1);

        when(repository.findAllByBirthdayBetween(from, to)).thenReturn(Flux.just(defaultUserDbo ,defaultUserDbo));

        StepVerifier.create(adapter.findByBirthdayRange(from, to))
                .expectNext(defaultUser)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findAllByBirthdayBetween(from, to);
    }
}