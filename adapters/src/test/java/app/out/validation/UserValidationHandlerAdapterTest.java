package app.out.validation;

import app.UserDto;
import app.exceptions.BirthdayRangeException;
import app.exceptions.InvalidDateException;
import app.exceptions.UserYoungException;
import app.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("unit")
class UserValidationHandlerAdapterTest {

    private final UserValidationHandlerAdapter adapter = new UserValidationHandlerAdapter();

    @Mock
    private BindingResult mockBindingResult;

    @BeforeEach
    public void setupTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Junit test for check user's age on valid")
    void checkAgeValid() {
        ReflectionTestUtils.setField(adapter, "min_age", 18);
        LocalDate birthday = LocalDate.now().minusYears(1);
        assertThrows(UserYoungException.class, () -> adapter.checkAgeValid(birthday));
    }

    @Test
    @DisplayName("Junit test for check user's birthday date on valid")
    void checkBirthDateValid() {
        LocalDate birthday = LocalDate.now().plusYears(1);
        assertThrows(InvalidDateException.class, () -> adapter.checkBirthDateValid(birthday));
    }

    @Test
    @DisplayName("Junit test for check birthday range on valid")
    void checkBirthdayRangeValid() {
        LocalDate from = LocalDate.now().plusYears(1);
        LocalDate to = LocalDate.now().minusYears(1);

        assertThrows(BirthdayRangeException.class, () -> adapter.checkBirthdayRangeValid(from, to));
    }

    @Test
    @DisplayName("Junit test for check dto for creating/updating on valid")
    void userDtoValidation() {
        UserDto dto = new UserDto();

        when(mockBindingResult.hasErrors()).thenReturn(true);
        assertThrows(ValidationException.class, () -> adapter.userDtoValidation(dto, mockBindingResult));
    }
}