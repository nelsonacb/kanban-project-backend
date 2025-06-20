package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(String id) {
        return repository.findById(id);
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(String id, Task updatedTask) {
        return repository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setColumn(updatedTask.getColumn());
                    return repository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    public void deleteTask(String id) {
        repository.deleteById(id);
    }
}