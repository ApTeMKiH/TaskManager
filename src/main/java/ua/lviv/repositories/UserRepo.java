package ua.lviv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.entity.User;

/**
 * Created by Артем on 5/31/2017.
 */
public interface UserRepo extends JpaRepository<User, Integer>{

    User findByLogin(String login);

    User findByEmail(String email);
}
