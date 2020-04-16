package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.exception.ResourceNotFoundException;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.service.TaskService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class ChatController {
    @Autowired
    TaskService taskService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/task/chat")
    public ModelAndView getChatPage(@RequestParam long id, Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Task> taskOptional = taskService.getTask(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
            if (!(user.getId().equals(task.getConsumer().getId()) || user.getId().equals(task.getVolunteer().getId()))){
                throw new AccessDeniedException("..");
            }
            modelAndView.addObject("task", taskOptional.get());
            modelAndView.setViewName("chat");
            return modelAndView;
        } else {
            throw new ResourceNotFoundException();
        }


    }




}
