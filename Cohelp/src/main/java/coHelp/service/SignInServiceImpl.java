package coHelp.service;

import coHelp.dto.SignInDto;
import coHelp.dto.TokenDto;
import coHelp.model.user.State;
import coHelp.model.user.User;
import coHelp.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Value("dkKef534cD2")
        private String secret;

        @Override
        public TokenDto signIn(SignInDto signInData) {
            // получаем пользователя по его email
            Optional<User> userOptional = userRepository.findByEmail(signInData.getEmail());
            // если у меня есть этот пользвователь
            if (userOptional.isPresent()) {
                // получаем его
                User user = userOptional.get();
                // если пароль подходит
                if (passwordEncoder.matches(signInData.getPassword(), user.getPassword())) {
                    if (user.getState().equals(State.NOT_CONFIRMED)){
                        throw new AccessDeniedException("Activate your account");
                    }
                    String token = Jwts.builder()
                            .setSubject(user.getId().toString()) // id пользователя
                            .claim("email", user.getEmail()) // имя
                            .claim("state", user.getState().name())
                            .claim("role", user.getRole().name()) // роль
                            .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                            .compact(); // преобразовали в строку
                    return new TokenDto(token);
                } else throw new AccessDeniedException("Wrong email/password");
            } else throw new AccessDeniedException("User not found");
        }
}
