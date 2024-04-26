package app.out.r2dbc;

import app.User;
import app.out.mapper.UserDboMapper;
import app.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class R2dbcUserRepositoryAdapter implements UserRepository {

    private final R2dbcUserRepository repository;

    @Override
    public Flux<User> findAll() {
        return repository
                .findAll()
                .map(UserDboMapper.mapper::toEntity);
    }

    @Override
    public Mono<User> findById(int id) {
        return repository
                .findById(id)
                .map(UserDboMapper.mapper::toEntity);
    }

    @Override
    @Transactional
    public Mono<User> save(User user) {
        return repository
                .save(UserDboMapper.mapper.toDbo(user))
                .map(UserDboMapper.mapper::toEntity);
    }

    @Override
    @Transactional
    public Mono<Void> delete(User user) {
        return repository
                .delete(UserDboMapper.mapper.toDbo(user));
    }

    @Override
    public Flux<User> findUsersFromBirthdayRange(LocalDate from, LocalDate to) {
        return repository
                .findAllByBirthdayBetween(from, to)
                .map(UserDboMapper.mapper::toEntity);
    }

}
