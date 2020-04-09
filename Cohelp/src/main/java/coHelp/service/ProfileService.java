package coHelp.service;

import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Optional<User> getProfile(Long id);

    List<Task> getMyTasks(User user);



}
