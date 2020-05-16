package chat.controller;

import chat.dto.UserDto;
import chat.model.User;
import chat.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class SignInController {

    @Autowired
    SignInService signInService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getPage() {
        return "signIn";
    }


    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam String nickname, @RequestParam String password, HttpSession httpSession) {
        UserDto userDto = UserDto.builder()
                .nickname(nickname)
                .password(password)
                .build();
        Optional<User> userOptional = signInService.signIn(userDto);
        if (userOptional.isPresent()) {
            httpSession.setAttribute("user", userOptional.get());
            return "redirect:/chats";
        } else return "redirect:/signIn";
    }

    @ModelAttribute("user")

    public User user() {
        return new User();
    }
}
