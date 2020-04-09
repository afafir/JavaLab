package coHelp.repository;

import coHelp.model.Address;
import coHelp.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;
@Repository
public class AddressRepositoryJpaImpl implements AddressRepository {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Address> findByUser(User user) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public Optional<Address> find(Long aLong) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public List<Address> findAll() {
        return null;
    }
    @Transactional
    @Override
    public Address save(Address entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Address update(Address entity) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long aLong) {

    }
}
