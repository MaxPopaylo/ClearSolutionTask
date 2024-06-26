package app;

import app.exceptions.UserNotFoundException;
import app.ports.UserDtoMapper;
import app.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository repository;
    private final UserDtoMapper mapper;

    @Override
    public Flux<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<User> getById(int id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    @Override
    public Mono<User> save(UserDto dto) {
        return repository
                .save(mapper.toUser(dto));
    }

    @Override
    public Mono<Void> delete(int id) {
        return getById(id)
                .flatMap(repository::delete)
                .then();
    }

    @Override
    public Mono<User> update(int id, UserDto dto) {
        return getById(id)
                .flatMap(val -> {

                    val.setEmail(StringUtils.defaultIfBlank(dto.getEmail(), val.getEmail()));
                    val.setFirst_name(StringUtils.defaultIfBlank(dto.getFirst_name(), val.getFirst_name()));
                    val.setLast_name(StringUtils.defaultIfBlank(dto.getLast_name(), val.getLast_name()));
                    val.setBirthday(dto.getBirthday() != null ? dto.getBirthday() : val.getBirthday());
                    val.setAddress(StringUtils.defaultIfBlank(dto.getAddress(), val.getAddress()));
                    val.setPhone_number(StringUtils.defaultIfBlank(dto.getPhone_number(), val.getPhone_number()));

                    return repository.save(val);
                });
    }

    @Override
    public Flux<User> getByBirthdayRange(LocalDate from, LocalDate to) {
        return repository.findByBirthdayRange(from, to);
    }

}

