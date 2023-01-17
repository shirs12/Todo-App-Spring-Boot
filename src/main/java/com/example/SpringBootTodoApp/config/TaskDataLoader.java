package com.example.SpringBootTodoApp.config;

import com.example.SpringBootTodoApp.models.Task;
import com.example.SpringBootTodoApp.repositories.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(TaskDataLoader.class);

    @Autowired
    TaskRepo taskRepo;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (taskRepo.count() == 0){  // if db is empty
            Task task1 = new Task("wash the dishes");
            Task task2 = new Task("get groceries");
            taskRepo.save(task1);
            taskRepo.save(task2);
        }
        logger.info("Number of tasks: {}", taskRepo.count());
    }
}
