package ua.lviv.service;

import ua.lviv.entity.Feedback;
import ua.lviv.entity.Task;
import ua.lviv.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
public interface FeedbackService {
    void add(String subject, String text, Date date, Task task, User user);

    void edit(int id, String subject, String text, Date date);

    void delete(int id);

    Feedback findById(int id);

    List<Feedback> findAllForTask(Task task);
}
