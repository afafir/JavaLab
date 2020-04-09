package coHelp.service;

import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.Role;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Optional<User> getProfile(Long id) {
        return  userRepository.find(id);
    }

    @Override
    public List<Task> getMyTasks(User user) {

        if (user.getRole().equals(Role.VOLUNTEER)){
            return taskRepository.findForVolunteer((Volunteer) user);
        } else return taskRepository.findForConsumer((Consumer) user);

    }


}
