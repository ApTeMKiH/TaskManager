package ua.lviv.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.entity.Task;
import ua.lviv.entity.Feedback;
import ua.lviv.entity.User;
import ua.lviv.repositories.FeedbackRepo;
import ua.lviv.service.FeedbackService;

import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepo feedbackRepo;

    public void add(String text, String mark, Date date, Task task, User user) {
        Feedback feedback = new Feedback(text, mark, date);
        feedback.setTask(task);
        feedback.setUser(user);
        feedbackRepo.save(feedback);
    }

    public void edit(int id, String text, String mark, Date date) {
        Feedback feedback = findById(id);
        if(text != null && !text.equalsIgnoreCase("")){
            feedback.setText(text);
        }
        if(mark != null && !mark.equalsIgnoreCase("")){
            feedback.setMark(mark);
        }
        feedbackRepo.save(feedback);
    }

    public void delete(int id) {
        feedbackRepo.delete(id);
    }

    public Feedback findById(int id) {
        return feedbackRepo.findOne(id);
    }

    public List<Feedback> findAllForTask(Task task) {
        return feedbackRepo.findByTask(task);
    }
}
