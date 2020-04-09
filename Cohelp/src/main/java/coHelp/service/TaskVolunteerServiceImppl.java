package coHelp.service;

import coHelp.model.task.State;
import coHelp.model.task.Task;
import coHelp.model.user.Volunteer;
import coHelp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskVolunteerServiceImppl implements TaskVolunteerService{


    @Autowired
    TaskRepository repository;

    @Override
    public void acceptTask(Task task, Volunteer volunteer) {
        task.setState(State.IN_PROGRESS);
        task.setVolunteer(volunteer);
        repository.update(task);
    }

    @Override
    public void confirmTask(Task task) {
        task.setState(State.CONFIRMED);
        repository.update(task);
    }
}
