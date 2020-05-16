package chat.service;

import chat.dto.MessageDto;
import chat.model.Message;

public interface MessageService {

    Message saveMessage(MessageDto messageDto);


}
