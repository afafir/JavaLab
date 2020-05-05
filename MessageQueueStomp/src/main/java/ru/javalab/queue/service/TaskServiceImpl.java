package ru.javalab.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.javalab.queue.consumer.TaskConsumer;
import ru.javalab.queue.model.Task;
import ru.javalab.queue.repository.TaskRepository;

import java.util.Map;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    Map<String, TaskConsumer> consumers;
    @Autowired
    SimpMessageSendingOperations messagingTemplate;


    @Override
    public Task saveTask(Task task) {
        taskRepository.save(task);
        if(!consumers.containsKey(task.getQueueName())) {
            consumers.put(task.getQueueName(), new TaskConsumer(task.getQueueName(), this, messagingTemplate));
        }
        synchronized (consumers.get(task.getQueueName())) {
            consumers.get(task.getQueueName()).notifyAll();
        }
        return task;
    }

    @Override
    public void completeTask(Task task) {
        task.setIsDone(true);
        taskRepository.save(task);
    }

    @Override
    public Task getLastUndoneTask() {
        return null;
    }

    @Override
    public Optional<Task> getLastUndoneTaskForQueue(String queue) {
        return taskRepository.findFirstByIsDoneFalseAndQueueNameIs(queue);
    }

    @Override
    public void doTask(Task task) {
        switch (task.getType()) {
            case SEND_DOC: {
                emailService.sendDoc(task.getEmail(), "document", "its doc mail for " + task.getEmail());
                break;
            }
            case SEND_MEME: {
                emailService.sendMeme(task.getEmail(), "meme");
                break;
            }
        }

    }
}