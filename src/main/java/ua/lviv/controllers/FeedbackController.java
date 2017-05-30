package ua.lviv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lviv.service.FeedbackService;
import ua.lviv.service.UserService;

import java.security.Principal;
import java.util.Date;

/**
 * Created by Артем on 4/27/2017.
 */
@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/tasks/feedback/delete/{id}", method = RequestMethod.GET)
    public String deleteFeedback(@PathVariable Integer id, Principal principal, Model model){
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        int ida = feedbackService.findById(id).getTask().getId();
        int idUserTask = feedbackService.findById(id).getTask().getUser().getId();
        if (idUserTask == userService.findByLogin(principal.getName()).getId() || userService.findByLogin(principal.getName()).getId() == feedbackService.findById(id).getUser().getId() ){
            feedbackService.delete(id);
            return "redirect:/tasks/task/"+ida;
        }else {
            return "redirect:/tasks/task/"+ida;
        }


    }

    @RequestMapping(value = "/tasks/feedback/edit/{id}", method = RequestMethod.GET)
    public String editFeedback(@PathVariable Integer id, Model model, Principal principal){
        model.addAttribute("currentUser", userService.findByLogin(principal.getName()));
        int ida = feedbackService.findById(id).getTask().getId();
        int idUserTask = feedbackService.findById(id).getTask().getUser().getId();
        if (idUserTask == userService.findByLogin(principal.getName()).getId() || userService.findByLogin(principal.getName()).getId() == feedbackService.findById(id).getUser().getId() ) {
            model.addAttribute("feedback", feedbackService.findById(id));
            return "editFeedback";
        }else {
            return "redirect:/tasks/task/"+ida;
        }
    }

    @RequestMapping(value = "/tasks/feedback/edit", method = RequestMethod.POST)
    public String editFeedback(@RequestParam("feedbackId") Integer id, @RequestParam("mark") String mark, @RequestParam("text") String text){
        feedbackService.edit(id, text, mark, new Date());

        return "redirect:/tasks/task/"+feedbackService.findById(id).getTask().getId();
    }
}
