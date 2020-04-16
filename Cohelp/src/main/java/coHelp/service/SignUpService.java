package coHelp.service;


import coHelp.dto.SignUpDto;
import coHelp.model.Mail;
import coHelp.model.Token;
import coHelp.model.user.User;


import java.util.Optional;

public interface SignUpService {
    Mail createMail(Token token);
    boolean signUp(SignUpDto dto);
    Optional<User> activate(String token);
    boolean isExist(User user);
}
