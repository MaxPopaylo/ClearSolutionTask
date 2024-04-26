package app.out.exception;

import app.exceptions.*;
import app.ports.UserExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandlerAdapter implements UserExceptionHandler {

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(InvalidDateException e) {
        return new ResponseError(e.getMessage());
    }

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(BirthdayRangeException e) {
        return new ResponseError(e.getMessage());
    }

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(UserYoungException e) {
        return new ResponseError(e.getMessage());
    }

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(UserNotFoundException e) {
        return new ResponseError(e.getMessage());
    }

    @Override
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ValidationException e) {
        return new ResponseError(e.getMessage());
    }

}
