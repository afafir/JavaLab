package coHelp.contoller.rest;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.service.TaskConsumerService;
import coHelp.service.TaskService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskConsumerRestController {
    @Autowired
    TaskConsumerService taskConsumerService;

    @Autowired
    TaskService taskService;


    @PreAuthorize("hasAuthority('CONSUMER')")
    @RequestMapping(value = "/rest/task/done/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> acceptTask(@PathVariable long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Task> taskOptional = taskService.getTask(taskId);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        if (!taskOptional.get().getConsumer().getId().equals(userDetails.getUserId())) {
            throw new AccessDeniedException("You cant update other user task");
        }
        return ResponseEntity.ok(taskConsumerService.acceptTask(taskOptional.get()));
    }

    @PreAuthorize("hasAuthority('CONSUMER')")
    @RequestMapping(value = "rest/task/reject/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> confirmTask(@PathVariable Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Task> taskOptional = taskService.getTask(taskId);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        if (!taskOptional.get().getConsumer().getId().equals(userDetails.getUserId())) {
            throw new AccessDeniedException("You cant update other user task");
        }
        return ResponseEntity.ok(taskConsumerService.rejectTask(taskOptional.get()));
    }


}
