package coHelp.repository;

import coHelp.model.Chat;
import coHelp.model.task.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepositoryJpaImpl implements ChatRepository {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;


    @Transactional
    @Override
    public Optional<Chat> findByTask(Task task) {

        Query query = entityManager.createNamedQuery("findChatForTask").setParameter("task", task);
        Chat chat;
        try {
            chat = (Chat) query.getSingleResult();
            entityManager.refresh(chat);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
        return Optional.of(chat);
    }

    @Transactional
    @Override
    public Optional<Chat> find(Long id) {
        Chat chat;
        try {
            chat = entityManager.find(Chat.class, id);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
        return Optional.of(chat);
    }

    @Transactional
    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Transactional
    @Override
    public Chat save(Chat entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Chat update(Chat entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
