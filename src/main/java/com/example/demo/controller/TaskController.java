package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getAll() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public  Optional<Task> getOne(@PathVariable String id) {
        return service.getTask(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteTask(id);
    }
}