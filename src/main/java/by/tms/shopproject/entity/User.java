package by.tms.shopproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Can't be empty!")
    @Size(min = 5, message = "At least 5 characters!")
    private String login;

    @NotNull(message = "Can't be empty!")
    @Size(min = 4, message = "At least 4 characters!")
    private String password;

    @NotNull(message = "Can't be empty!")
    @Size(min = 4, message = "At least 4 characters!")
    private String name;

    @NotNull(message = "Can't be empty!")
    @Size(min = 7, message = "At least 7 characters!")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
