package app.ports;

import app.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface UserRepository {

    Flux<User> findAll();
    Mono<User> findById(int id);
    Mono<User> save(User user);
    Mono<Void> delete(User user);
    Flux<User> findUsersFromBirthdayRange(LocalDate from, LocalDate to);

}
