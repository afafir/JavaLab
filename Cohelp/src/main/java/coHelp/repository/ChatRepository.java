package coHelp.repository;

import coHelp.model.Chat;
import coHelp.model.task.Task;

import javax.persistence.Id;
import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    Optional<Chat> findByTask(Task task);

}
