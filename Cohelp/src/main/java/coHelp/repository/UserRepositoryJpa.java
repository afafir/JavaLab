package coHelp.repository;

import coHelp.model.user.User;
import org.postgresql.largeobject.LargeObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {
}
