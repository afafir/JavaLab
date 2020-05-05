package ru.javalab.queue.service;

import ru.javalab.queue.model.Task;

import java.util.Optional;

public interface TaskService {
    Task saveTask(Task task);
    void completeTask(Task task);
    Task getLastUndoneTask();
    Optional<Task> getLastUndoneTaskForQueue(String queue);
    void doTask(Task task);
}
