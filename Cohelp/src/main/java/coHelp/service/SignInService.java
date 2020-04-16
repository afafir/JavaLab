package coHelp.service;

import coHelp.dto.SignInDto;
import coHelp.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
