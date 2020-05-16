package chat.controller;

import chat.model.Chat;
import chat.model.Message;
import chat.model.User;
import chat.service.ChatService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("user")
public class ChatController {

    @Autowired
    ChatService chatService;


    @GetMapping("/chat")
    public ModelAndView getPage(@RequestParam Long id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user.getCookie() == null) {
            return new ModelAndView("redirect:/signIn");
        } else {
            ModelAndView chat = new ModelAndView("chat");
            chat.addObject("user", user);
            Chat chatObj = chatService.getChat(id).get();
            //Hibernate.initialize(chatObj.getMessageList());
            chat.addObject("chat", chatObj);

            return chat;
        }
    }


}
