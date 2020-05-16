package chat.controller;

import chat.dto.UserDto;
import chat.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    @Autowired
    SignUpService signUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getPage() {
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestParam String nickname, @RequestParam String password) {
        UserDto userDto = UserDto.builder()
                .nickname(nickname)
                .password(password)
                .build();
        if (signUpService.signUp(userDto)) {
            return "redirect:/signIn";
        } else return "redirect:/signUp";
    }


}
