package coHelp.contoller;

import coHelp.dto.SignUpDto;
import coHelp.service.SignUpService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;


    @RequestMapping("/signUp")
    public String mainPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        model.addAttribute("signUpDto", new SignUpDto());
        return "signUp";
    }


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(@Valid SignUpDto form, BindingResult bindingResult, Model model) {
        form.setCity("Казань");
        System.out.println(bindingResult.getAllErrors());
        if ((bindingResult.hasErrors())) {
            model.addAttribute("signUpDto", form);
            return "signUp";
        } else {
           if(signUpService.signUp(form)){
               return "redirect:/signIn";
           }
        }
        return "redirect:/";
    }

}
