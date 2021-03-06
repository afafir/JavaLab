package coHelp.repository;

import coHelp.dto.DocumentInformationDto;
import coHelp.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return Optional.empty();
        }
        return Optional.of(user);
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

    @Override
    public Optional<DocumentInformationDto> findInfo(Long id){
        Query query = entityManager.createNamedQuery("findInformation").setParameter("userId", id);
        DocumentInformationDto documentInformationDto;
        try{
            documentInformationDto = (DocumentInformationDto) query.getSingleResult();
        }catch (NoResultException nre){
            return Optional.empty();
        }
        return Optional.of(documentInformationDto);

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
