package by.tms.shopproject.repository;

import by.tms.shopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByLogin(String login);
    public boolean existsUserByLogin(String login);
}
