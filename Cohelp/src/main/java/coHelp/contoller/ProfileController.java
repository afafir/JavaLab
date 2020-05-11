package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.user.UserDto;
import coHelp.exception.ResourceNotFoundException;
import coHelp.model.user.User;
import coHelp.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Optional;


@Controller
@RequestScope
public class ProfileController {

    @Autowired
    ProfileService profileService;
    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "404";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getPage(Authentication authentication, Model model) {
        Long id = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
       model.addAttribute("user", profileService.getProfile(id));
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


    @SneakyThrows
    @RequestMapping(value = "profile/upload_avatar", method = RequestMethod.POST)
    public @ResponseBody String uploadAvatar(Authentication authentication, @RequestParam CommonsMultipartFile file){


        if (!profileService.uploadAvatar(file, ((UserDetailsImpl)authentication.getPrincipal()).getUser())){
            throw new IllegalArgumentException();
        } else return objectMapper.writeValueAsString("success");
    }
}
