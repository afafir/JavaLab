package downloader.services;


import downloader.dto.SignUpDto;
import downloader.models.user.Token;

public interface SignUpService {
    Token signUp(SignUpDto dto);
}
