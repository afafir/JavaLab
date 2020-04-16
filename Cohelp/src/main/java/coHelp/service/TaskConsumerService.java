package coHelp.service;

import coHelp.dto.TaskDto;
import coHelp.model.task.Task;

public interface TaskConsumerService {
    Task createTask(TaskDto taskDto);
    Task acceptTask(Task task);
    Task rejectTask(Task task);
}
