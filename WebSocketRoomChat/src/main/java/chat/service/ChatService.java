package chat.service;

import chat.model.Chat;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<Chat> getAllChats();

    Optional<Chat> getChat(Long id);

    Chat saveChat(Chat chat);
}
