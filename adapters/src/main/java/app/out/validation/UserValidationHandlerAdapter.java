package app.out.validation;

import app.UserDto;
import app.exceptions.*;
import app.ports.UserValidationHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

@Component
public class UserValidationHandlerAdapter implements UserValidationHandler {

    @Value("${properties.user.min_age}")
    private int min_age;

    @Override
    public void checkAgeValid(LocalDate birthday) {
        if (birthday != null) {
            int age = LocalDate.now().getYear() - birthday.getYear();
            if (age < min_age) {
                throw new UserYoungException(min_age);
            }
        }
    }

    @Override
    public void checkBirthDateValid(LocalDate birthDate) {
        if (birthDate != null && birthDate.isAfter(LocalDate.now())) {
            throw new InvalidDateException();
        }
    }

    @Override
    public void checkBirthdayRangeValid(LocalDate from, LocalDate to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new BirthdayRangeException();
        }
    }

    @Override
    public void userDtoValidation(UserDto dto, BindingResult bindingResult) {
        checkBirthDateValid(dto.getBirthday());
        checkAgeValid(dto.getBirthday());

        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

    }


}
