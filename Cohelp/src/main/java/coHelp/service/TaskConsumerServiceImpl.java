package coHelp.service;

import coHelp.dto.TaskDto;
import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.model.task.Type;
import coHelp.model.user.Consumer;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskConsumerServiceImpl implements TaskConsumerService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Task createTask(TaskDto taskDto) {
            Task task = Task.builder().description(taskDto.getDescription()).state(State.ACTIVE).build();
            switch (taskDto.getType()) {
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
        task.setConsumer((Consumer) userRepository.find(taskDto.getConsumer()).get());
        return taskRepository.save(task);
    }

    public List<Task> getMyTasks(Consumer consumer){
        return taskRepository.findForConsumer(consumer);
    }

    @Override
    public void acceptTask(Task task) {
        task.setState(State.DONE);
        taskRepository.update(task);

    }

    @Override
    public void rejectTask(Task task) {
        task.setState(State.ACTIVE);
        taskRepository.update(task);
    }
}
