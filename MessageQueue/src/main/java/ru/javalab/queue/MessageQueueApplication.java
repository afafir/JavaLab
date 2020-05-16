package ru.javalab.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.socket.WebSocketSession;
import ru.javalab.queue.consumer.TaskConsumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class MessageQueueApplication {

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }

    @Bean
    public Map<String, TaskConsumer> taskConsumerMap(){
        return new HashMap<>();
    }

    @Bean
    public Map<String, List<WebSocketSession>> sessions(){
        return new HashMap<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageQueueApplication.class, args);
    }

}
