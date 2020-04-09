package coHelp.repository;

import coHelp.model.user.User;
import coHelp.model.user.Volunteer;

import java.util.Optional;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    Optional<Volunteer> findByUser(User user);
}
