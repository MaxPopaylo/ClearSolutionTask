package app.out.r2dbc;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Table(name = "books")
public class UserDbo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String email;
    private String first_name;
    private String last_name;
    private LocalDate birthday;
    private String address;
    private String phone;

}
