package coHelp.repository;

import coHelp.model.document.Avatar;
import coHelp.model.document.Document;
import coHelp.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
@Repository("jpa")
public class AvatarRepositoryJpaImpl implements AvatarRepository {


    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    @Override
    public Optional<Avatar> findByOwner(User user) {
        Query query = entityManager.createNamedQuery("findByOwner").setParameter("user", user);
        Avatar avatar = null;
        try {
            avatar = (Avatar) query.getSingleResult();
        } catch (NoResultException nre) {
            return Optional.empty();
        }
        return Optional.of(avatar);
    }
    @Transactional
    @Override
    public Optional<Avatar> find(Long aLong) {
        Avatar avatar = entityManager.find(Avatar.class, aLong);
        if (avatar != null) {
            return Optional.of(avatar);
        }
        return Optional.empty();
    }
    @Transactional
    @Override
    public List<Avatar> findAll() {
        return null;
    }
    @Transactional
    @Override
    public Avatar save(Avatar entity) {
        entityManager.persist(entity);
        return entity;
    }
    @Transactional
    @Override
    public Avatar update(Avatar entity) {
        entityManager.merge(entity);
        return entity;
    }
    @Transactional
    @Override
    public void delete(Long aLong) {

    }
}
