package coHelp.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<V, ID> {
    Optional<V> find(ID id);
    List<V> findAll();
    V save(V entity);
    V update(V entity);
    void delete(ID id);
}
