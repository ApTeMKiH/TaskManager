package ua.lviv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lviv.entity.Feedback;
import ua.lviv.entity.Task;
import ua.lviv.service.TaskService;
import ua.lviv.service.FeedbackService;
import ua.lviv.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, Principal principal){
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        List<Task> tasks = taskService.findAllForUser(userService.findByLogin(principal.getName()));
        Collections.reverse(tasks);
        model.addAttribute("tasks", tasks);
        return "home";
    }
    @RequestMapping(value = "/tasks/own", method = RequestMethod.GET)
    public String FromYou(Model model, Principal principal){
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        List<Task> tasks = taskService.findByUser(userService.findByLogin(principal.getName()));
        Collections.reverse(tasks);
        model.addAttribute("tasks", tasks);
        return "fromYou";
    }

    @RequestMapping(value = "/tasks/task/{id}", method = RequestMethod.GET)
    public String getSingleProduct(@PathVariable Integer id, Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        model.addAttribute("taskId", id);
        if (principal != null && !(principal.getName().equals("admin"))){
            model.addAttribute("user", userService.findByLogin(principal.getName()));
        }
        List<Feedback> feedbacks = taskService.findById(id).getFeedbackList();
        Collections.reverse(feedbacks);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("task", taskService.findById(id));
        return "task";
    }

    @RequestMapping(value = "/tasks/task", method = RequestMethod.POST)
    public String addFeedback(@RequestParam("artId") Integer id, @RequestParam("mark") String mark, @RequestParam("text") String text, Principal principal){
        feedbackService.add(text,mark,new Date(), taskService.findById(id),userService.findByLogin(principal.getName()));
        return "redirect:/tasks/task/"+id;
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.GET)
    public String addNewProduct(Principal principal, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        return "addTask";
    }

    @RequestMapping(value = "/tasks/task/user", method = RequestMethod.GET)
    @ResponseBody
    public int checkNewTask(Principal principal){
        return taskService.findAllForUser(userService.findByLogin(principal.getName())).size();
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String addNewProduct(@RequestParam("subject") String subject, @RequestParam("text") String text, @RequestParam("toWhom") String email, Principal principal) {
        taskService.add(subject, text, new Date(), userService.findByLogin(principal.getName()), email);
        return "redirect:/";
    }

    @RequestMapping(value = "/tasks/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Integer id, Principal principal, Model model) {
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        Task task = taskService.findById(id);
        if (task.getUser().getId() != userService.findByLogin(principal.getName()).getId()){
            return "redirect:/tasks/task/"+id;
        }else {
            taskService.delete(id);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/tasks/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable Integer id, Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        Task task = taskService.findById(id);
        if (task.getUser().getId() != userService.findByLogin(principal.getName()).getId()){
            return "redirect:/tasks/task/"+id;
        }else {
            model.addAttribute("tasks", task);
            return "editTask";
        }
    }
    @RequestMapping(value = "/tasks/edit", method = RequestMethod.POST)
    public String editProduct(@RequestParam("id")Integer id, @RequestParam("subject") String subject, @RequestParam("text") String text) {
        taskService.edit(id, subject, text, new Date());
        return "redirect:/tasks/task/"+id;
    }
}
