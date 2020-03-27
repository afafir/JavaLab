package downloader.services;


import downloader.dto.SignUpDto;
import downloader.dto.UserDto;
import downloader.models.user.Token;

import java.util.Optional;

public interface SignUpService {
    Token signUp(SignUpDto dto);
    Optional<UserDto> activate(String token);
}
