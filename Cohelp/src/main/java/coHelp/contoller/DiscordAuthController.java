package coHelp.contoller;

import bell.oauth.discord.main.OAuthBuilder;
import coHelp.config.security.details.UserDetailsImpl;
import coHelp.model.user.User;
import coHelp.repository.UserRepository;
import com.vk.api.sdk.actions.OAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Controller
public class DiscordAuthController {

    @Autowired
    OAuthBuilder oAuthBuilder;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/discord")
    public ModelAndView getDiscordAuth(@RequestParam("code") String code) throws IOException {
        oAuthBuilder.exchange(code);
        Optional<User> user = userRepository.findByEmail(oAuthBuilder.getUser().getEmail());
        if (!user.isPresent()) {
            return new ModelAndView("redirect:signIn");
        } else {
            UserDetailsImpl userDetails = new UserDetailsImpl(user.get());
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(userDetails, user.get().getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return new ModelAndView("redirect:profile");
        }
    }
}
