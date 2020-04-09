package coHelp.service;

import coHelp.dto.TaskDto;
import coHelp.model.task.Task;

public interface TaskConsumerService {
    Task createTask(TaskDto taskDto);
    void acceptTask(Task task);
    void rejectTask(Task task);
}
