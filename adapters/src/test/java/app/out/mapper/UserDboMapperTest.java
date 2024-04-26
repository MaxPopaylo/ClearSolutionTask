package app.out.mapper;

import app.User;
import app.out.r2dbc.UserDbo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class UserDboMapperTest {

    private final User defaultUser = new User();
    private final UserDbo defaultUserDbo = new UserDbo();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void init() {
        defaultUser.setId(1);
        defaultUser.setFirst_name("First Name");
        defaultUser.setLast_name("Last Name");
        defaultUser.setEmail("email@email.com");
        defaultUser.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUser.setAddress("Address 1");
        defaultUser.setPhone_number("Phone Number");

        defaultUserDbo.setId(1);
        defaultUserDbo.setFirst_name("First Name");
        defaultUserDbo.setLast_name("Last Name");
        defaultUserDbo.setEmail("email@email.com");
        defaultUserDbo.setBirthday(LocalDate.of(1990, 1, 1));
        defaultUserDbo.setAddress("Address 1");
        defaultUserDbo.setPhone_number("Phone Number");
    }

    @Test
    @DisplayName("Junit test for mapping User into UserDbo")
    void toDbo() {
        UserDbo dbo = UserDboMapper.mapper.toDbo(defaultUser);

        assertNotNull(dbo);
        assertEquals(dbo.getFirst_name(), defaultUser.getFirst_name());
        assertEquals(dbo.getBirthday(), defaultUser.getBirthday());
    }

    @Test
    @DisplayName("Junit test for mapping UserDbo into User")
    void toEntity() {
        User user = UserDboMapper.mapper.toEntity(defaultUserDbo);

        assertNotNull(user);
        assertEquals(user.getFirst_name(), defaultUserDbo.getFirst_name());
        assertEquals(user.getBirthday(), defaultUserDbo.getBirthday());
    }
}