package app.ports;

import app.UserDto;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

public interface UserValidationHandler {

    void checkAgeValid(LocalDate birthday);
    void checkBirthDateValid(LocalDate birthDate);
    void checkBirthdayRangeValid(LocalDate from, LocalDate to);

    void userDtoValidation(UserDto dto, BindingResult bindingResult);

}
