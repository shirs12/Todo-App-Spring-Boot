package com.example.SpringBootTodoApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    private String createdTime;

    @Getter
    @Setter
    private String modifiedTime;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.complete = false;
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.systemDefault());
        this.createdTime = dateTime.format(Instant.now());
        this.modifiedTime = dateTime.format(Instant.now());
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, description='%s', complete='%s', createdTime='%s', modifiedTime='%s'}",
                id, description, complete, createdTime, modifiedTime);
    }
}
