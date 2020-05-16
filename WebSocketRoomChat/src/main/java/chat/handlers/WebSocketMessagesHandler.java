package chat.handlers;

import chat.dto.MessageDto;
import chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Long, Map<String, WebSocketSession>> sessions = new HashMap<>();


    @Bean
    public Map<Long, Map<String, WebSocketSession>> sessions() {
        return sessions;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        MessageDto messageFromJson = objectMapper.readValue(messageText, MessageDto.class);
        messageService.saveMessage(messageFromJson);
        if (!sessions.containsKey(messageFromJson.getFrom())) {
            sessions.put(messageFromJson.getFrom(), new HashMap<>());
        }

        if (!sessions.get(messageFromJson.getFrom()).containsKey(messageFromJson.getUserFrom())) {
            sessions.get(messageFromJson.getFrom()).put(messageFromJson.getUserFrom(), session);
        }


        if (session.getC)

            for (WebSocketSession currentSession : sessions.get(messageFromJson.getFrom()).values()) {
                try {
                    currentSession.sendMessage(message);
                } catch (Exception ex) {
                    synchronized (sessions.get(messageFromJson.getFrom())) {
                        sessions.get(messageFromJson.getFrom()).values().remove(currentSession);
                    }
                }
            }
    }
}
