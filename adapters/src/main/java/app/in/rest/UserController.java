package app.in.rest;

import app.*;
import app.ports.UserValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserValidationHandler validation;

    @GetMapping
    public Flux<User> getUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/birthday/range")
    public Flux<User> getByBirthdayRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        validation.checkBirthdayRangeValid(from, to);
        return service.getByBirthdayRange(from,to);
    }

    @PostMapping
    public Mono<User> create(@RequestBody @Validated(ValidationGroup.Creating.class) UserDto dto,
                             BindingResult bindingResult) {
        validation.userDtoValidation(dto, bindingResult);
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@RequestBody @Validated(ValidationGroup.Updating.class) UserDto dto,
                             BindingResult bindingResult, @PathVariable int id) {
        validation.userDtoValidation(dto, bindingResult);
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }


}

