package coHelp.service;

import coHelp.model.task.Task;
import coHelp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.find(id);
    }
}
