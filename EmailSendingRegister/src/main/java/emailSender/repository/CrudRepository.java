package emailSender.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<K, V> {
    V save(V data);
    void update(V data);
    void delete(K id);
    Optional<V> find(K id);
    List<V> findAll();

}
