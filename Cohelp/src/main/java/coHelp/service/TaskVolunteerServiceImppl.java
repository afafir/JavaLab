package coHelp.service;

import coHelp.model.Chat;
import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.model.user.Volunteer;
import coHelp.repository.ChatRepository;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TaskVolunteerServiceImppl implements TaskVolunteerService{


    @Autowired
    TaskRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;
    @Override
    public Task acceptTask(Task task, Long volunteerId) {
        if (!task.getState().equals(State.ACTIVE)){
            throw new AccessDeniedException("Improper state change");
        }
        task.setState(State.IN_PROGRESS);
        task.setVolunteer((Volunteer) userRepository.find(volunteerId).get());
        repository.update(task);
        chatRepository.save(Chat.builder().task(task).build());
        return  task;
    }

    @Override
    public Task confirmTask(Task task) {
        if (!task.getState().equals(State.IN_PROGRESS)){
            throw new AccessDeniedException("Improper state change");
        }
        task.setState(State.CONFIRMED);
        return repository.update(task);
    }
}
