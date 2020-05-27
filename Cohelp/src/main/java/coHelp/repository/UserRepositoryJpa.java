package coHelp.repository;

import coHelp.dto.DocumentInformationDto;
import coHelp.model.user.User;
import org.postgresql.largeobject.LargeObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
