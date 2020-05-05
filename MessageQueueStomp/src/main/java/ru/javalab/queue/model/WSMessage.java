package ru.javalab.queue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WSMessage {
    private String command;
    private String messageId;
    private String queueName;
    private Map<String, Object> body;


}
