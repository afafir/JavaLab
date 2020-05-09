package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.user.UserDto;
import coHelp.exception.ResourceNotFoundException;
import coHelp.model.user.User;
import coHelp.service.ProfileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;


@Controller
@RequestScope
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "404";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getPage(Authentication authentication, Model model) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        user.setId(user.getId());
        model.addAttribute("tasks", profileService.getMyTasks(user));
        return "profile";
    }


    @RequestMapping(value = "/profile/id{id}", method = RequestMethod.GET)
    public String getUserPage(@PathVariable("id") Long id, Authentication authentication, Model model) {
        if (authentication != null) {
            User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
            if (user.getId().equals(id)) {
                return "redirect:/profile";
            }
        }
        UserDto user = profileService.getProfile(id);
        model.addAttribute("user", user);
        return "anotherProfile";
    }
}
