package coHelp.repository;

import coHelp.model.Chat;
import coHelp.model.Message;
import coHelp.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryJpaImpl implements MessageRepository {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Message> find(Long aLong) {
        Message message;
        try {
            message = entityManager.find(Message.class, aLong);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
        return Optional.of(message);
    }

    @Transactional
    @Override
    public List<Message> findAll() {
        return entityManager.createQuery("SELECT message FROM Message message", Message.class).getResultList();
    }

    @Transactional
    @Override
    public Message save(Message entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Message update(Message entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }
}
