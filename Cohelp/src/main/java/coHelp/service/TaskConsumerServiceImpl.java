package coHelp.service;

import coHelp.dto.TaskPostDto;
import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.model.task.Type;
import coHelp.model.user.Consumer;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumerServiceImpl implements TaskConsumerService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Task createTask(TaskPostDto taskPostDto) {
        Task task = Task.builder().description(taskPostDto.getDescription()).state(State.ACTIVE).build();
        switch (taskPostDto.getType()) {
            case "walking":
                task.setType(Type.PET_WALKING);
                break;
            case "products":
                task.setType(Type.PRODUCTS);
                break;
            case "medicines":
                task.setType(Type.MEDICINES);
                break;
        }
        task.setConsumer((Consumer) userRepository.find(taskPostDto.getConsumer()).get());
        return taskRepository.save(task);
    }


    @Override
    public Task acceptTask(Task task) {
        if (!task.getState().equals(State.CONFIRMED)) {
            throw new AccessDeniedException("improper state change");
        }
        task.setState(State.DONE);
        return taskRepository.update(task);

    }

    @Override
    public Task rejectTask(Task task) {
        task.setVolunteer(null);
        task.setState(State.ACTIVE);
        return taskRepository.update(task);
    }
}
