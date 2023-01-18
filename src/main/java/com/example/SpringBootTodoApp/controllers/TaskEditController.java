package com.example.SpringBootTodoApp.controllers;

import com.example.SpringBootTodoApp.models.Task;
import com.example.SpringBootTodoApp.repositories.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskEditController {
    private final Logger logger = LoggerFactory.getLogger(TaskEditController.class);

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") long id, Model model){
        logger.debug("request to PUT task");
        Task task = taskRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Task: " + id + "not found"));
        model.addAttribute("task", task);
        return "edit-task";
    }

}
