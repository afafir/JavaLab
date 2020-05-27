package coHelp.config.security;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class FacebookSignInAdapter implements SignInAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public String signIn(
            String localUserId,
            Connection<?> connection,
            NativeWebRequest request) {

        UserDetailsImpl userDetails = new UserDetailsImpl(userRepository.findByEmail(connection.getDisplayName()).get());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities()));

        return null;
    }


}
