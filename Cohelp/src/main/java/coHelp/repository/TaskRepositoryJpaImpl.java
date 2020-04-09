package coHelp.repository;

import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryJpaImpl implements TaskRepository {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Task> find(Long aLong) {
        Task task = entityManager.find(Task.class, aLong);
        if (task != null) {
            return Optional.of(task);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT task FROM Task task", Task.class).getResultList();
    }

    @Transactional
    @Override
    public Task save(Task entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Task update(Task entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }


    @Override
    public List<Task> findForConsumer(Consumer consumer) {
        Query query = entityManager.createNamedQuery("findTasksForConsumer").setParameter("consumer", consumer);
        return query.getResultList();
    }

    @Override
    public List<Task> findForVolunteer(Volunteer volunteer) {
        Query query = entityManager.createNamedQuery("findTasksForVolunteer").setParameter("volunteer", volunteer);
        return query.getResultList();
    }
}
