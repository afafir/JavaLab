package emailSender.repository;

import emailSender.model.Token;
import emailSender.model.User;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Long, Token> {
    Optional<Token> findByUser(User user);

    Optional<Token> findByToken(String tokenStr);
}
