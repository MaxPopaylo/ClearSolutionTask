package app;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    @Email(groups = {ValidationGroup.Creating.class, ValidationGroup.Updating.class}, message = "Invalid email")
    @NotNull(groups = {ValidationGroup.Creating.class}, message = "Email can't be empty")
    private String email;

    @NotNull(groups = {ValidationGroup.Creating.class}, message = "Name can't be empty")
    private String first_name;

    @NotNull(groups = {ValidationGroup.Creating.class}, message = "Surname can't be empty")
    private String last_name;

    @NotNull(groups = {ValidationGroup.Creating.class}, message = "Birthday date can't be empty")
    private LocalDate birthday;

    private String address;
    private String phone_number;

}
