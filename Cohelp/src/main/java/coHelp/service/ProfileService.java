package coHelp.service;

import coHelp.dto.user.UserDto;
import coHelp.model.task.Task;
import coHelp.model.user.User;

import java.util.List;

public interface ProfileService {
    UserDto getProfile(Long id);

    List<Task> getMyTasks(User user);



}
