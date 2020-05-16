package chat.service;

import chat.model.Chat;
import chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Optional<Chat> getChat(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public Chat saveChat(Chat chat) {
        chatRepository.save(chat);
        return chat;
    }
}
