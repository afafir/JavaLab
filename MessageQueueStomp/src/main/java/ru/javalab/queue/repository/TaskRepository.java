package ru.javalab.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javalab.queue.model.Task;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findFirstByIsDoneFalseAndQueueNameIs(String queue);
}
