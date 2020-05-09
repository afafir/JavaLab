package coHelp.service;

import coHelp.model.task.Task;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getTask(Long id);

    List<Task> getActiveTasks();
}
