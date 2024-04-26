package app.in.rest;

import app.User;
import app.UserDto;
import app.UserService;
import app.ValidationGroup;
import app.ports.UserValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PostMapping
    public Mono<User> create(@RequestBody @Validated(ValidationGroup.Creating.class) UserDto dto,
                             BindingResult bindingResult) {
        validation.userDtoValidation(dto, bindingResult);
        return service.save(dto);
    }

}