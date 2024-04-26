package app;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface UserService {

    Flux<User> getAll();
    Mono<User> getById(int id);
    Mono<User> save(UserDto dto);
    Mono<Void> delete(int id);
    Mono<User> update(int id, UserDto dto);
    Flux<User> getByBirthdayRange(LocalDate from, LocalDate to);

}
