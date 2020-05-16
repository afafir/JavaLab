package downloader.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignInController {
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "signIn";
    }

}
