package chat.service;

import chat.dto.UserDto;

public interface SignUpService {
    boolean signUp(UserDto userDto);
}
