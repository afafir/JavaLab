package coHelp.service;

import coHelp.dto.TaskPostDto;
import coHelp.model.task.Task;

public interface TaskConsumerService {
    Task createTask(TaskPostDto taskPostDto);

    Task acceptTask(Task task);

    Task rejectTask(Task task);
}
