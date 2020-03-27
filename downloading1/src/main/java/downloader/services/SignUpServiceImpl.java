package downloader.services;

import downloader.dto.SignUpDto;
import downloader.models.user.Role;
import downloader.models.user.State;
import downloader.models.user.Token;
import downloader.models.user.User;
import downloader.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Token signUp(SignUpDto dto) {
        User user = User.builder().email(dto.getEmail())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .token(Token.builder().token(UUID.randomUUID().toString()).build())
                .build();
        user.getToken().setUser(user);
        userRepository.save(user);
        return user.getToken();
    }
}
