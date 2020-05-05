package ru.javalab.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import ru.javalab.queue.model.Task;
import ru.javalab.queue.model.Type;
import ru.javalab.queue.model.WSMessage;
import ru.javalab.queue.service.TaskService;

import java.util.UUID;

@Controller
public class MessageController {
    @Autowired
    TaskService taskService;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/task/{queueName}/send")
    @SendTo("/queue/{queueName}")
    public void createTask(@Payload WSMessage wsMessage, @DestinationVariable String queueName){
        Task task = Task.builder().email((String) wsMessage.getBody().get("email"))
                .type(Type.valueOf(wsMessage.getBody().get("type").toString()))
                .generatedId(UUID.randomUUID().toString())
                .isDone(false)
                .queueName(wsMessage.getQueueName())
                .build();
        System.out.println(task.getQueueName());
        taskService.saveTask(task);
        messagingTemplate.convertAndSend("/queue/"+task.getQueueName(),WSMessage.builder()
                .messageId(task.getGeneratedId())
                .command("accepted")
                .queueName(wsMessage.getQueueName())
                .build());
    }

}
