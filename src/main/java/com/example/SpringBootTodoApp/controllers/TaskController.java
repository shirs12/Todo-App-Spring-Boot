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
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Controller
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("tasksList", taskRepo.findAll());
        modelAndView.addObject("today", String.valueOf(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek()).toLowerCase());
        return modelAndView;
    }

    @PostMapping("/task")
    public String addTask(@Valid Task task, BindingResult result, Model model){
        if (result.hasErrors()) return "add-task";
        task.setCreatedTime(dateTime.format(Instant.now()));
        task.setModifiedTime(dateTime.format(Instant.now()));
        taskRepo.save(task);
        return "redirect:/";
    }

    @PostMapping("/task/{id}")
    public String EditTask(@PathVariable("id") long id, @Valid Task task, BindingResult result, Model model){
        if (result.hasErrors()){
            task.setId(id);
            return "edit-task";
        }
        task.setModifiedTime(dateTime.format(Instant.now()));
        System.out.println();
        taskRepo.save(task);
        return "redirect:/";
    }


}
