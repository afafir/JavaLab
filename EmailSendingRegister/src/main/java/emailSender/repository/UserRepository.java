package emailSender.repository;

import emailSender.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    
    
}
