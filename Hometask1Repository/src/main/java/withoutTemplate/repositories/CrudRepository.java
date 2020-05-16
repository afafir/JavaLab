package withoutTemplate.repositories;

import model.Author;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, L> {
    Author save(T data);

    void update(T data);

    void delete(L id);

    Optional<T> find(L id);

    List<T> findAll();

}
