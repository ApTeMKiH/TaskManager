package ua.lviv.service;

import ua.lviv.entity.User;

import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
public interface UserService {
    void addUser(String firstName, String lastName, String email, String phone, String login, String password);

    User findByLogin(String login);

    User findByEmail(String email);

    List<User> findAll();
}
