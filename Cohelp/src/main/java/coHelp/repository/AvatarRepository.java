package coHelp.repository;

import coHelp.model.document.Avatar;
import coHelp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends CrudRepository<Avatar, Long>{
    Optional<Avatar> findByOwner(User user);
}
