package chat.controller;

import chat.model.Chat;
import chat.model.User;
import chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ChatsController {
    @Autowired
    ChatService chatService;

    @GetMapping("/chats")
    public ModelAndView getPage(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user.getCookie() == null) {
            return new ModelAndView("redirect:/signIn");
        } else {
            ModelAndView chat = new ModelAndView("chats");
            chat.addObject("chats", chatService.getAllChats());
            chat.addObject("user", user);
            return chat;
        }
    }

    @PostMapping("/chats")
    public String createChat(@RequestParam String name) {
        Chat chat = Chat.builder().name(name).build();
        chatService.saveChat(chat);
        return "redirect:/chats";
    }


}
