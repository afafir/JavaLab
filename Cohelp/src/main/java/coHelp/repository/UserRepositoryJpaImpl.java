package coHelp.repository;

import coHelp.model.user.Role;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository

public class UserRepositoryJpaImpl implements UserRepository {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {
        Query query = entityManager.createNamedQuery("findByEmail").setParameter("email", email);
        User user = (User) query.getSingleResult();
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<User> find(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();

    }

    @Transactional
    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public User update(User entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
