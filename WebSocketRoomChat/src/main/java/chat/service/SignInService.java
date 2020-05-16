package chat.service;

import chat.dto.UserDto;
import chat.model.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(UserDto userDto);
}
