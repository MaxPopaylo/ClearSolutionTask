package app.ports;

import app.UserDto;

import java.time.LocalDate;

public interface UserValidationHandler {

    Boolean checkEmailUnique(String email);
    Boolean checkEmailValid(String email);
    Boolean checkAgeValid(Integer age);
    Boolean checkBirthDateValid(LocalDate birthDate);
    Boolean checkBirthdayRangeValid(LocalDate from, LocalDate to);

    void userDtoValidation(UserDto userDto);

}
