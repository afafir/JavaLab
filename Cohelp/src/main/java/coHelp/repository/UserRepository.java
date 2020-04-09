package coHelp.repository;

import coHelp.model.user.User;
import coHelp.model.user.Volunteer;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
