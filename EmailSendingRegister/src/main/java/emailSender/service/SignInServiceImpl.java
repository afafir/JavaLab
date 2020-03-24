package emailSender.service;

import emailSender.dto.SignInDto;
import emailSender.dto.UserDto;
import emailSender.model.User;
import emailSender.repository.UserRepository;
import emailSender.util.exception.AuthenticationException;
import emailSender.util.exception.ConfirmationException;
import emailSender.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public UserDto signIn(SignInDto dto) {
        Optional<User> user = repository.findByName(dto.getName());
        if (!user.isPresent()){
            throw new UserNotFoundException("User not found");
        } else if (!encoder.matches(dto.getPassword(), user.get().getPassword())){
            throw new AuthenticationException("Invalid data entered");
        } else if (!user.get().isEnabled())
            throw new ConfirmationException("Activate account please");
        return UserDto.from(user.get());
    }
}
