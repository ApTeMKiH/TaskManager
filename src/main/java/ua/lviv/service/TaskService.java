package ua.lviv.service;

import ua.lviv.entity.Task;
import ua.lviv.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
public interface TaskService {
    void add(String subject, String text, Date date, User user, String email);

    void edit(int id, String subject, String text, Date date);

    List<Task> findAllForUser(User user);
    List<Task> findByUser(User user);

    void delete(int id);

    Task findById(int id);

    List<Task> findAll();
}
