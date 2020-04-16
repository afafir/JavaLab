package coHelp.service;

import coHelp.dto.MessageDto;
import coHelp.model.Message;
import coHelp.model.task.Task;
import coHelp.repository.ChatRepository;
import coHelp.repository.MessageRepository;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public void saveMessage(MessageDto messageDto) {
        Message message = Message.builder()
                .message(messageDto.getMessage())
                .chat(chatRepository.findByTask(taskRepository.find(messageDto.getTaskId()).get()).get())
                .sender(userRepository.find(messageDto.getSenderId()).get())
                .build();
        messageRepository.save(message);
    }

    @Override
    public List<MessageDto> getAllMessages(Long taskId) {
        return chatRepository.findByTask(taskRepository.find(taskId).get()).get().
                getMessageList()
                .stream()
                .map(x->new MessageDto(x.getSender().getName()+" "+x.getSender().getSurname(),x.getMessage()))
                .collect(Collectors.toList());
    }
}
