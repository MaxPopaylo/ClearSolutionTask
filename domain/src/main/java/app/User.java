package app;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private LocalDate birthday;
    private String address;
    private String phone_number;

}
