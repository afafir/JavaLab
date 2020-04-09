package coHelp.service;

import coHelp.model.task.Task;
import coHelp.model.user.Volunteer;

public interface TaskVolunteerService {

    void acceptTask(Task task, Volunteer volunteer);

    void confirmTask(Task task);


}
