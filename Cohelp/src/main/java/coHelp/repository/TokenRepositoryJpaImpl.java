package coHelp.repository;

import coHelp.model.Token;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Repository
public class TokenRepositoryJpaImpl implements TokenRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Token> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }
    @Transactional
    @Override
    public Token save(Token entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Token update(Token entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Token> findByToken(String token) {
        Query query = entityManager.createNamedQuery("findByToken").setParameter("token", token);
        Token toReturn = (Token) query.getSingleResult();
        if (toReturn != null){
            return Optional.of(toReturn);
        }
        return Optional.empty();
    }
}
