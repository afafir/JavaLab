package emailSender.service;

import emailSender.dto.SignUpDto;
import emailSender.dto.UserDto;
import emailSender.model.User;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

public interface SignUpService {
    UserDto SignUp(SignUpDto dto) throws AuthenticationException;
    boolean isExist(User user);
    Optional<UserDto> activate(String token);
}
