package coHelp.repository.jdbc;

import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.Volunteer;
import coHelp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryJdbcTemplateImpl implements TaskRepository {


    @Autowired
    private JdbcTemplate template;

    private RowMapper<Task> taskRowMapper = (ResultSet rs, int row) -> {
return null;
    };


    @Override
    public List<Task> findForConsumer(Consumer consumer) {
        return null;
    }

    @Override
    public List<Task> findForVolunteer(Volunteer volunteer) {
        return null;
    }

    @Override
    public Optional<Task> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task save(Task entity) {
        return null;
    }

    @Override
    public Task update(Task entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
