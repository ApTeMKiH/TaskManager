package ua.lviv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.entity.Feedback;
import ua.lviv.entity.Task;

import java.util.List;

/**
 * Created by Артем on 5/31/2017.
 */
public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

    List<Feedback> findByTask(Task task);
}
