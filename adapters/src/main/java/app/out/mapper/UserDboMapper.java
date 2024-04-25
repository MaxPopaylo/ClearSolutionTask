package app.out.mapper;

import app.User;
import app.out.r2dbc.UserDbo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDboMapper {

    UserDboMapper mapper = Mappers.getMapper( UserDboMapper.class );

    UserDbo toDbo(User user);
    User toEntity(UserDbo dbo);

}
