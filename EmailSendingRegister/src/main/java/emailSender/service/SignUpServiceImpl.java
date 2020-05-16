package emailSender.service;

import emailSender.dto.SignUpDto;
import emailSender.dto.UserDto;
import emailSender.model.Mail;
import emailSender.model.Token;
import emailSender.model.User;
import emailSender.repository.TokenRepository;
import emailSender.repository.UserRepository;
import emailSender.util.exception.EmailExistException;
import emailSender.util.exception.MailUtil.PreparerMail;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailService service;
    @Autowired
    private PreparerMail preparerMail;

    @Override
    public UserDto SignUp(SignUpDto dto) {
        User user = User.fromDto(dto);
        if (isExist(user)) {
            throw new EmailExistException("This username or email are engaged");
        }
        user.setPassword(encoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        service.sendMessage(preparerMail.createActivateMail(user));
        return UserDto.from(user);
    }

    @Override
    public boolean isExist(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByName(user.getName()).isPresent();
    }

    @Override
    public Optional<UserDto> activate(String token) {
        Optional<Token> toActivate = tokenRepository.findByToken(token);
        if (toActivate.isPresent()) {
            User user = toActivate.get().getUser();
            user.setEnabled(true);
            userRepository.update(user);
            return Optional.of(UserDto.from(user));
        } else return Optional.empty();
    }


}
