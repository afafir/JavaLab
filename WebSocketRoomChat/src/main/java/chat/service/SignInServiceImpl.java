package chat.service;

import chat.dto.UserDto;
import chat.model.User;
import chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> signIn(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByNickname(userDto.getNickname());
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userDto.getPassword())) {
            return optionalUser;
        } else return Optional.empty();
    }
}
