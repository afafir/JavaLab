package chat.service;

import chat.dto.MessageDto;
import chat.model.Message;
import chat.repository.ChatRepository;
import chat.repository.MessageRepository;
import chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;

    @Override
    public Message saveMessage(MessageDto messageDto) {
        Message message = Message.builder().text(messageDto.getText())
                .sender(userRepository.findByNickname(messageDto.getUserFrom()).get())
                .chat(chatRepository.getOne(messageDto.getFrom()))
                .build();
        messageRepository.save(message);
        return message;
    }
}
