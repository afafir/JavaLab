package chat.service;

import chat.dto.UserDto;
import chat.model.User;
import chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean signUp(UserDto userDto) {
        try {
            userRepository.save(User.builder().nickname(userDto.getNickname())
                    .password(userDto.getPassword())
                    .cookie(UUID.randomUUID().toString()).build());
            return true;
        } catch (DataIntegrityViolationException ex) {
            return false;
        }

    }
}
