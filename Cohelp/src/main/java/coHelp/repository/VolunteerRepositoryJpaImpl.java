package coHelp.repository;

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
public class VolunteerRepositoryJpaImpl implements VolunteerRepository {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;


    @Override
    public Optional<Volunteer> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Volunteer> findAll() {
        return entityManager.createQuery("SELECT volunteer FROM Volunteer volunteer", Volunteer.class).getResultList();
    }

    @Transactional
    @Override
    public Volunteer save(Volunteer entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Volunteer update(Volunteer entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Volunteer> findByUser(User user) {
        //Query query = entityManager.createNamedQuery("findByUserVolunteer").setParameter("user", user);
        // VolunteerDto volunteer = (VolunteerDto) query.getSingleResult();
        //if (volunteer != null){
        //    return Optional.of(volunteer);
        //}
        return Optional.empty();
    }
}
