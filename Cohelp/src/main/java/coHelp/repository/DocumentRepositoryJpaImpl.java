package coHelp.repository;

import coHelp.model.document.Document;
import coHelp.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.Doc;
import java.util.List;
import java.util.Optional;
@Repository
public class DocumentRepositoryJpaImpl implements DocumentRepository{


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Document> find(Long aLong) {
        Document document = entityManager.find(Document.class, aLong);
        if (document != null) {
            return Optional.of(document);
        }
        return Optional.empty();
    }
    @Transactional
    @Override
    public List<Document> findAll() {
        return entityManager.createQuery("SELECT document FROM Document document", Document.class).getResultList();
    }
    @Transactional
    @Override
    public Document save(Document entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Document update(Document entity) {
        return  entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        entityManager.remove(find(aLong));
    }
}
