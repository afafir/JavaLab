package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.service.TaskService;
import coHelp.service.TaskVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskVolunteerController {

 @Autowired
    TaskVolunteerService taskVolunteerService;

 @Autowired
    TaskService taskService;

    @PreAuthorize("hasAuthority('VOLUNTEER')")
    @RequestMapping(value = "/task/accept", method = RequestMethod.POST)
    public String acceptTask(@RequestParam(value = "taskId") Long id, Authentication authentication) {
        User user =((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Task task = taskService.getTask(id).get();
        taskVolunteerService.acceptTask(task, user.getId());
        return "redirect:/task?id="+id;
    }

    @PreAuthorize("hasAuthority('VOLUNTEER')")
    @RequestMapping(value = "/task/confirm", method = RequestMethod.POST)
    public String confirmTask(@RequestParam(value = "taskId") Long id) {
        Task task = taskService.getTask(id).get();
        taskVolunteerService.confirmTask(task);
        return "redirect:/task?id="+id;
    }


}
