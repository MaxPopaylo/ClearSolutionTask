package app.ports;

import app.User;
import app.UserDto;

public interface UserDtoMapper {

    UserDto toDto(User user);
    User toUser(UserDto userDto);

}
