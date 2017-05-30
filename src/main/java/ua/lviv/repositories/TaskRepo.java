package ua.lviv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.entity.Task;
import ua.lviv.entity.User;

import java.util.List;

/**
 * Created by Артем on 5/31/2017.
 */
public interface TaskRepo extends JpaRepository<Task, Integer>{
    List<Task> findByUserTo(User user);

    List<Task> findByUser(User user);
}
