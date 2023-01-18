package com.example.SpringBootTodoApp.controllers;

import com.example.SpringBootTodoApp.models.Task;
import com.example.SpringBootTodoApp.repositories.TaskRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;


@Controller
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("tasksList", taskRepo.findAll());
        return modelAndView;
    }

    @PostMapping("/task/{id}")
    public String EditTask(@PathVariable("id") long id, @Valid Task task, BindingResult result, Model model){
        if (result.hasErrors()){
            task.setId(id);
            return "edit-task";
        }
        task.setModifiedTime(Instant.now());
        taskRepo.save(task);
        return "redirect:/";
    }


}
