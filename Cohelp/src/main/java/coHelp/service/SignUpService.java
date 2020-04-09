package coHelp.service;


import coHelp.dto.SignUpDto;
import coHelp.model.Mail;
import coHelp.model.Token;
import coHelp.model.user.User;


import java.util.Optional;

public interface SignUpService {
    Mail createMail(Token token);
    Mail signUp(SignUpDto dto);
    Optional<User> activate(String token);
}
