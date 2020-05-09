package coHelp.service;

import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.find(id);
    }

    @Override
    public List<Task> getActiveTasks() {
        return taskRepository.findAll().stream().filter(x -> x.getState().equals(State.ACTIVE)).collect(Collectors.toList());
    }

}
