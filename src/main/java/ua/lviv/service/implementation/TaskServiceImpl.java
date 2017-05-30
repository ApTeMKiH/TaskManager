package ua.lviv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.entity.Task;
import ua.lviv.entity.User;
import ua.lviv.repositories.TaskRepo;
import ua.lviv.service.TaskService;
import ua.lviv.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserService userService;

    public void add(String subject, String text, Date date, User user, String email) {
        Task task = new Task(subject, text, date);
        task.setUser(user);
        task.setUserTo(userService.findByEmail(email));
        taskRepo.save(task);
    }

    public List<Task> findByUser(User user) {
        return taskRepo.findByUser(user);
    }

    public void edit(int id, String subject, String text, Date date) {
        Task task = findById(id);
        if(subject != null && !subject.equalsIgnoreCase("")){
            task.setSubject(subject);
        }
        if(text != null && !text.equalsIgnoreCase("")){
            task.setText(text);
        }
        taskRepo.save(task);
    }

    public List<Task> findAllForUser(User user) {
        return taskRepo.findByUserTo(user);
    }

    public void delete(int id) {
        taskRepo.delete(id);
    }

    public Task findById(int id) {
        return taskRepo.findOne(id);
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }
}
