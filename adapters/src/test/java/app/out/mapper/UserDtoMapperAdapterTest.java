package app.out.mapper;

import app.User;
import app.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class UserDtoMapperAdapterTest {

    private final UserDtoMapperAdapter adapter = new UserDtoMapperAdapter();

    private final User defaultUser = new User();
    private final UserDto defaultUserDto = new UserDto();

    @BeforeEach
    public void init() {
        defaultUser.setId(1);
        defaultUser.setFirst_name("First Name");
        defaultUser.setLast_name("Last Name");
        defaultUser.setEmail("email@email.com");
        defaultUser.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUser.setAddress("Address 1");
        defaultUser.setPhone_number("Phone Number");

        defaultUserDto.setFirst_name("First Name");
        defaultUserDto.setLast_name("Last Name");
        defaultUserDto.setEmail("email@email.com");
        defaultUserDto.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUserDto.setAddress("Address 1");
        defaultUserDto.setPhone_number("Phone Number");
    }

    @Test
    @DisplayName("Junit test for mapping User into UserDto")
    void toDto() {
        UserDto dto = adapter.toDto(defaultUser);

        assertNotNull(dto);
        assertEquals(dto.getFirst_name(), defaultUser.getFirst_name());
        assertEquals(dto.getBirthday(), defaultUser.getBirthday());
    }

    @Test
    @DisplayName("Junit test for mapping UserDto into User")
    void toUser() {
        User user = adapter.toUser(defaultUserDto);

        assertNotNull(user);
        assertEquals(user.getFirst_name(), defaultUserDto.getFirst_name());
        assertEquals(user.getBirthday(), defaultUserDto.getBirthday());
    }
}