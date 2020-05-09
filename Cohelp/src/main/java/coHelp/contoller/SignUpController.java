package coHelp.contoller;

import coHelp.dto.SignUpDto;
import coHelp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;


    @RequestMapping("/signUp")
    public String mainPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "signUp";
    }


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(SignUpDto form) {
        form.setCity("Казань");
        if (signUpService.signUp(form)) {
            return "redirect:/signIn";

        } else return "redirect:/signUp";
    }

}
