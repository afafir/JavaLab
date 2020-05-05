package ru.javalab.queue.wsHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.javalab.queue.consumer.TaskConsumer;
import ru.javalab.queue.model.Task;
import ru.javalab.queue.model.Type;
import ru.javalab.queue.model.WSMessage;
import ru.javalab.queue.service.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TaskService taskService;
    @Autowired
    Map<String, TaskConsumer> consumers;


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WSMessage wsMessage = objectMapper.readValue(message.getPayload(), WSMessage.class);
        System.out.println(wsMessage.getCommand());
        switch (wsMessage.getCommand()) {
            case "subscribe": {
                if (!consumers.containsKey(wsMessage.getQueueName())) {
                    TaskConsumer taskConsumer = new TaskConsumer(wsMessage.getQueueName(), Collections.singletonList(session), taskService);
                    applicationContext.getAutowireCapableBeanFactory().autowireBean(taskConsumer);
                    consumers.put(wsMessage.getQueueName(), taskConsumer);
                } else {
                    List<WebSocketSession> sessions = consumers.get(wsMessage.getQueueName()).getWebSocketSessions();
                    sessions.add(session);
                    consumers.get(wsMessage.getQueueName()).setWebSocketSessions(sessions);
                }
                break;
            }
            case "send": {
                Task task = Task.builder().email((String) wsMessage.getBody().get("email"))
                        .type(Type.valueOf(wsMessage.getBody().get("type").toString()))
                        .generatedId(UUID.randomUUID().toString())
                        .isDone(false)
                        .queueName(wsMessage.getQueueName())
                        .build();
                taskService.saveTask(task);
                session.sendMessage(new TextMessage(
                        objectMapper.writeValueAsString(
                                WSMessage.builder()
                                        .messageId(task.getGeneratedId())
                                        .command("accepted")
                                        .queueName(task.getQueueName())
                                        .build())));
                synchronized (consumers.get(task.getQueueName())) {
                    consumers.get(task.getQueueName()).notifyAll();
                }
            }

        }
    }
}
