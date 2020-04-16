package coHelp.service;

import coHelp.model.task.Task;
import coHelp.model.user.Volunteer;

public interface TaskVolunteerService {

    Task acceptTask(Task task, Long volunteerId);

    Task confirmTask(Task task);


}
