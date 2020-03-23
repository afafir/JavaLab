package security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import security.dto.SignUpDto;
import security.models.user.Role;
import security.models.user.State;
import security.models.user.User;
import security.repositories.UserRepository;
@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void SignUp(SignUpDto dto) {
        userRepository.save(User.builder().email(dto.getEmail())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .build());
    }
}
