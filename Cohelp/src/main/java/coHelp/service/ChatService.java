package coHelp.service;

import coHelp.dto.MessageDto;
import coHelp.model.Message;
import coHelp.model.task.Task;

import java.util.List;

public interface ChatService {
    void saveMessage(MessageDto message);
    List<MessageDto> getAllMessages(Long chatId);



}
