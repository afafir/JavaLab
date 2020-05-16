package withoutTemplate.repositories;

import model.Author;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
