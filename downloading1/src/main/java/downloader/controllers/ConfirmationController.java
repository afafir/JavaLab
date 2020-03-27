package downloader.controllers;

import downloader.dto.UserDto;
import downloader.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public ModelAndView getPage(@RequestParam(value = "token") String token, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("signIn");
        Optional<UserDto> user = signUpService.activate(token);
        if (user.isPresent()) {
            modelAndView.addObject("message", user.get().getEmail() + " activated");
        } else modelAndView.addObject("message", "user not found");
        return modelAndView;
    }

}
