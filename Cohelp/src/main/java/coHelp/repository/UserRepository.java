package coHelp.repository;

import coHelp.dto.DocumentInformationDto;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<DocumentInformationDto> findInfo(Long id);


}
