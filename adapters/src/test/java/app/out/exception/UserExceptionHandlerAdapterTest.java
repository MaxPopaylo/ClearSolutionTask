package app.out.exception;

import app.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class UserExceptionHandlerAdapterTest {

    private final UserExceptionHandlerAdapter adapter = new UserExceptionHandlerAdapter();

    @Test
    @DisplayName("Junit test for handling InvalidDateException")
    void handleInvalidDateException() {
        ResponseError handled = adapter.handle(new InvalidDateException());
        InvalidDateException e = new InvalidDateException();

        assertEquals(handled.getMessage(), e.getMessage());
    }

    @Test
    @DisplayName("Junit test for handling BirthdayRangeException")
    void handleBirthdayRangeException() {
        ResponseError handled = adapter.handle(new BirthdayRangeException());
        BirthdayRangeException e = new BirthdayRangeException();

        assertEquals(handled.getMessage(), e.getMessage());
    }

    @Test
    @DisplayName("Junit test for handling UserYoungException")
    void handleUserYoungException() {
        ResponseError handled = adapter.handle(new UserYoungException(18));
        UserYoungException e = new UserYoungException(18);

        assertEquals(handled.getMessage(), e.getMessage());
    }

    @Test
    @DisplayName("Junit test for handling UserNotFoundException")
    void handleUserNotFoundException() {
        ResponseError handled = adapter.handle(new UserNotFoundException());
        UserNotFoundException e = new UserNotFoundException();

        assertEquals(handled.getMessage(), e.getMessage());
    }

    @Test
    @DisplayName("Junit test for handling ValidationException")
    void handleValidationException() {
        String message = "some error message";
        ResponseError handled = adapter.handle(new ValidationException(message));

        assertEquals(handled.getMessage(), message);
    }

}


