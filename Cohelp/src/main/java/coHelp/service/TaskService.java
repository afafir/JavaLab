package coHelp.service;

import coHelp.dto.TaskGetDto;
import coHelp.model.task.Task;
import coHelp.model.user.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getTask(Long id);

    List<TaskGetDto> getActiveTasks();

    List<TaskGetDto> getTasksForUserDistrict(User user);

    List<TaskGetDto> getTaskForDistance(Long km, User user);
}
