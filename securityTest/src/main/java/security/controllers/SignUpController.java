package security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import security.dto.SignUpDto;
import security.services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getPage() {
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(SignUpDto form) {
        signUpService.SignUp(form);
        return "redirect:/signIn";
    }
}
