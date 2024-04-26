package app.out.r2dbc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface R2dbcUserRepository extends ReactiveCrudRepository<UserDbo, Integer> {
    Flux<UserDbo> findAllByBirthdayBetween(LocalDate from, LocalDate to);
    Mono<UserDbo> findByEmail(String email);
}
