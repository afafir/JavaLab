package ru.javalab.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.javalab.queue.model.Task;
import ru.javalab.queue.model.WSMessage;
import ru.javalab.queue.service.TaskService;
import ru.javalab.queue.service.TaskServiceImpl;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class TaskConsumer extends Thread {
    //один консамер на очередь
    private String queue;
    private TaskService taskService;
    private ObjectMapper objectMapper;
    List<WebSocketSession> webSocketSessions;


    public TaskConsumer(String queue, List<WebSocketSession> webSocketSessions, TaskService taskService) {
        this.queue = queue;
        this.webSocketSessions = webSocketSessions;
        this.objectMapper = new ObjectMapper();
        this.taskService = taskService;
        this.start();
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                Optional<Task> taskOpt = taskService.getLastUndoneTaskForQueue(queue);
                if (!taskOpt.isPresent()) {
                    this.wait();
                } else {
                    Task task = taskOpt.get();
                    for (WebSocketSession session : webSocketSessions) {
                        session.sendMessage(new TextMessage(
                                objectMapper.writeValueAsString(
                                        WSMessage.builder()
                                                .command("received")
                                                .messageId(task.getGeneratedId())
                                                .queueName(task.getQueueName())
                                                .build())));
                    }
                    taskService.doTask(task);
                    taskService.completeTask(task);
                    for (WebSocketSession session : webSocketSessions) {
                        session.sendMessage(new TextMessage(
                                objectMapper.writeValueAsString(
                                        WSMessage.builder()
                                                .queueName(task.getQueueName())
                                                .messageId(task.getGeneratedId())
                                                .command("completed")
                                                .build())));
                    }
                }

            }
        }
    }

}
