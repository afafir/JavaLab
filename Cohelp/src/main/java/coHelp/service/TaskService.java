package coHelp.service;

import coHelp.model.task.Task;

import java.util.Optional;

public interface TaskService {
    Optional<Task> getTask(Long id);
}
