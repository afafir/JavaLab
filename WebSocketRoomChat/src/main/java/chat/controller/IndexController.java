package chat.controller;

import chat.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getPage(@ModelAttribute User user) {
        if (user.getCookie() == null) {
            return "redirect:/signIn";
        } else {
            return "redirect:/chats";
        }

    }
}