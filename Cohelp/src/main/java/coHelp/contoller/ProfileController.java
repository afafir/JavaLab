package coHelp.contoller;

import coHelp.config.details.UserDetailsImpl;
import coHelp.dto.TaskDto;
import coHelp.exception.ResourceNotFoundException;
import coHelp.model.user.User;
import coHelp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;


@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "404";
    }


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
        Optional<User> user = profileService.getProfile(id);
        if (user.isPresent()){
            model.addAttribute("user", user.get());
            return "anotherProfile";
        } else {
            throw new ResourceNotFoundException();
        }

    }
}
