package emailSender.service;

import emailSender.dto.SignInDto;
import emailSender.dto.UserDto;

public interface SignInService {
    UserDto signIn(SignInDto dto);

}
