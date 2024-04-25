package app.ports;

import app.exceptions.*;

public interface UserExceptionHandler {

    ResponseError handle(EmailAlreadyUsedException e);
    ResponseError handle(EmailWrongException e);
    ResponseError handle(InvalidDateException e);
    ResponseError handle(BirthdayRangeException e);
    ResponseError handle(UserYoungException e);
    ResponseError handle(UserNotFoundException e);
    ResponseError handle(ValidationException e);

}
