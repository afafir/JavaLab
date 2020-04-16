package coHelp.contoller.rest;


import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.MessageDto;
import coHelp.service.ChatServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {

    private static final Map<Long, Map<Long, List<MessageDto>>>      messages = new HashMap<>();

    @Autowired
    private ChatServiceImpl chatService;

    @GetMapping("/allmessages")
    public ResponseEntity<List<MessageDto>> getAllMessagesForPage(@RequestParam(value = "taskId")Long taskId, Authentication authentication) {
        if (!messages.containsKey(taskId)) {
            messages.put(taskId, new HashMap<>());
        }
        Long userId = ((UserDetailsImpl)authentication.getPrincipal()).getUser().getId();
        if (!messages.get(taskId).containsKey(userId))
        {
            messages.get(taskId).put(userId, new ArrayList<>());
        }
        return ResponseEntity.ok(chatService.getAllMessages(taskId));
    }

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        chatService.saveMessage(message);
        Map<Long, List<MessageDto>> currentTask = messages.get(message.getTaskId());
        synchronized (currentTask.values()){
        for (List<MessageDto> pageMessages : currentTask.values()) {
            synchronized (pageMessages) {
                pageMessages.add(message);
                pageMessages.notifyAll();
            }
        }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("taskId") Long taskId, Authentication authentication) {
        Long userId = ((UserDetailsImpl)authentication.getPrincipal()).getUser().getId();
        Map<Long, List<MessageDto>> currentTask = messages.get(taskId);
        synchronized (currentTask.get(userId)) {
            if (currentTask.get(userId).isEmpty()) {
                currentTask.get(userId).wait();
            }
        }
        List<MessageDto> response = new ArrayList<>(currentTask.get(userId));
        currentTask.get(userId).clear();
        return ResponseEntity.ok(response);
    }







}
