package coHelp.contoller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @RequestMapping("/signIn")
    public String mainPage(Authentication authentication) {

        if (authentication !=null){
            return "redirect:/profile";
        }
        return "signIn";
    }

}