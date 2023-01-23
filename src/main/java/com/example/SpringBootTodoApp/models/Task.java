package com.example.SpringBootTodoApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required.")
    private String description;

    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private Instant createdTime;

    @Getter
    @Setter
    private Instant modifiedTime;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.complete = false;
        this.createdTime = Instant.now();
        this.modifiedTime = Instant.now();
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, description='%s', complete='%s', createdTime='%s', modifiedTime='%s'}",
                id, description, complete, createdTime, modifiedTime);
    }
}
