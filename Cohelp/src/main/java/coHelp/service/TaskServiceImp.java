package coHelp.service;

import coHelp.dto.TaskGetDto;
import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.repository.TaskRepository;
import coHelp.util.Standartization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    Standartization standartization;

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.find(id);
    }

    @Override
    public List<TaskGetDto> getActiveTasks() {
        return taskRepository.findAll().stream().
                filter(x -> x.getState().equals(State.ACTIVE))
                .map(Task::toTaskGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskGetDto> getTasksForUserDistrict(User user) {
        return taskRepository.findAll().stream()
                .filter(x -> x.getConsumer().getAddress().getDistrict().equals(user.getAddress().getDistrict()))
                .filter(x -> x.getState().equals(State.ACTIVE))
                .map(Task::toTaskGetDto)
                .collect(Collectors.toList());
    }


    //cos(d) = sin(φА)·sin(φB) + cos(φА)·cos(φB)·cos(λА − λB),
    //L = d·R, r=6371
    @Override
    public List<TaskGetDto> getTaskForDistance(Long km, User user) {
        String userAddress = user.getAddress().getStringAddress();
        Map.Entry userCoords = standartization.getCoord(userAddress);
        List<Task> allTasks = taskRepository.findAll();
        List<TaskGetDto> toReturn = new ArrayList<>();
        for (Task task : allTasks) {
            String consumerAddress = task.getConsumer().getAddress().getStringAddress();
            Map.Entry consumerCoords = standartization.getCoord(consumerAddress);
            Double distance = standartization.getDistance(userCoords, consumerCoords);
            if (distance <= km){
                toReturn.add(task.toTaskGetDto());
            }
        }
        return  toReturn;
    }

}
