package coHelp.repository;

import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long>{
    List<Task> findForConsumer(Consumer consumer);
    List<Task> findForVolunteer(Volunteer volunteer);
}
