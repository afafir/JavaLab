package coHelp.contoller.rest;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.service.TaskService;
import coHelp.service.TaskVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class TaskVolunteerRestController {

    @Autowired
    TaskVolunteerService taskVolunteerService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task/accept/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> acceptTask(@PathVariable(value = "taskId") Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Task> taskOptional = taskService.getTask(taskId);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskVolunteerService.acceptTask(taskOptional.get(), userDetails.getUserId()));
    }


    @RequestMapping(value = "/task/confirm/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> confirmTask(@RequestParam(value = "taskId") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Task> task = taskService.getTask(id);
        if (task.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        if (!task.get().getVolunteer().getId().equals(userDetails.getUserId())) {
            throw new AccessDeniedException("You cant update other user tasks");
        }
        return ResponseEntity.ok(task.get());
    }

}
