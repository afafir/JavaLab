package downloader.controllers;

import downloader.dto.SignUpDto;
import downloader.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        signUpService.signUp(form);
        return "redirect:/signIn";
    }
}
