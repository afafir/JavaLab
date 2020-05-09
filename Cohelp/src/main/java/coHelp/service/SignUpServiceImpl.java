package coHelp.service;

import coHelp.dto.SignUpDto;
import coHelp.model.Address;
import coHelp.model.Mail;
import coHelp.model.Token;
import coHelp.model.user.Role;
import coHelp.model.user.State;
import coHelp.model.user.User;
import coHelp.repository.TokenRepository;
import coHelp.repository.UserRepository;
import coHelp.util.MailPreparer;
import coHelp.util.Standartization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    //чтобы работал аспект
    @Autowired
    SignUpService signUpService;

    @Autowired
    MailPreparer mailPreparer;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private Standartization standartization;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(SignUpDto dto) {
        Address address = Address.builder()
                .city(dto.getCity())
                .street(dto.getStreet())
                .house(dto.getHouse())
                .district(standartization.cleanAddress(dto.getCity() + " " + dto.getStreet() + " " + dto.getHouse()).getCityDistrict())
                .build();
        User user = User.builder()
                .address(address)
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getTel())
                .state(State.NOT_CONFIRMED)
                .role(dto.getRole().equals("Волонтёр") ? Role.VOLUNTEER : Role.CONSUMER)
                .build();
        if (user.getRole().equals(Role.VOLUNTEER)) {
            user = user.toVolunteer();
        } else user = user.toConsumer();
        if (isExist(user)) {
            return false;
        } else {
            Token token = Token.builder()
                    .token(UUID.randomUUID().toString())
                    .user(user).build();
            tokenRepository.save(token);

            //чтобы работал аспект
            signUpService.createMail(token);
            return true;
        }
    }


    @Override
    public Mail createMail(Token token) {
        Map<String, Object> model = new HashMap();
        model.put("link", token.getToken());
        String html = mailPreparer.getMailTemplate(model);
        return Mail.builder().mailFrom("javalabqwerty@gmail.com")
                .mailSubject("Activate your account")
                .mailContent(html)
                .model(model)
                .mailTo(token.getUser().getEmail())
                .mailContent(html)
                .build();
    }


    @Override
    public Optional<User> activate(String token) {
        Optional<Token> toActivate = tokenRepository.findByToken(token);
        if (toActivate.isPresent()) {
            User user = toActivate.get().getUser();
            user.setState(State.CONFIRMED);
            userRepository.update(user);
            return Optional.of(user);
        } else return Optional.empty();
    }

    @Override
    public boolean isExist(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
