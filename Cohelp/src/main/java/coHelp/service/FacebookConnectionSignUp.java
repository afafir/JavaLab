package coHelp.service;

import coHelp.model.user.Role;
import coHelp.model.user.State;
import coHelp.model.user.User;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignUp implements ConnectionSignUp {


    @Autowired
    private UserRepository usersRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = User.builder()
                .email(connection.getDisplayName())
                .build();
        if (!usersRepository.findByEmail(user.getEmail()).isPresent()){
            return null;
        }else
            user = usersRepository.findByEmail(user.getEmail()).get();
        return user.getEmail();
    }
}
