package app.out.mapper;

import app.User;
import app.UserDto;
import app.ports.UserDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserDtoMapperAdapter implements UserDtoMapper {

    @Override
    public UserDto toDto(User user) {
        return UserMapper.userMapper.toDto(user);
    }

    @Override
    public User toUser(UserDto dto) {
        return UserMapper.userMapper.toEntity(dto);
    }

}
