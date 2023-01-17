package com.example.SpringBootTodoApp.repositories;

import com.example.SpringBootTodoApp.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {



}
