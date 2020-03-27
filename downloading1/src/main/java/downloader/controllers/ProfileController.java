package downloader.controllers;

import downloader.config.securityConf.details.UserDetailsImpl;
import downloader.models.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProfileController {
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "profile";
    }

}
