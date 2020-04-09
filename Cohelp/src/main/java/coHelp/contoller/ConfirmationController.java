package coHelp.contoller;

import coHelp.model.user.User;
import coHelp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ConfirmationController {
    @Autowired
    SignUpService signUpService;

    @RequestMapping(value = "/activation", method = RequestMethod.GET, params = {"token"})
    public ModelAndView getPage(@RequestParam(value = "token") String token) {
        ModelAndView modelAndView = new ModelAndView("signIn");
        Optional<User> user = signUpService.activate(token);
        if (user.isPresent()) {
            modelAndView.addObject("message", user.get().getEmail() + " activated");
        } else modelAndView.addObject("message", "user not found");
        return modelAndView;
    }

}
