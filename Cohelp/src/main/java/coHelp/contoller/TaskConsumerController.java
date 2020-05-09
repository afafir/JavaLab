package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.TaskDto;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.service.TaskConsumerService;
import coHelp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class TaskConsumerController {

    @Autowired
    TaskConsumerService taskConsumerService;


    @Autowired
    TaskService taskService;

    @PreAuthorize("hasAuthority('CONSUMER')")
    @RequestMapping(value = "/task/done", method = RequestMethod.POST)
    public String acceptTask(@RequestParam(value = "taskId") Long id, Authentication authentication) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Task task = taskService.getTask(id).get();
        taskConsumerService.acceptTask(task);
        return "redirect:/task?id=" + id;
    }

    @PreAuthorize("hasAuthority('CONSUMER')")
    @RequestMapping(value = "/task/reject", method = RequestMethod.POST)
    public String confirmTask(@RequestParam(value = "taskId") Long id) {
        Task task = taskService.getTask(id).get();
        taskConsumerService.rejectTask(task);
        return "redirect:/task?id=" + id;
    }

    @PreAuthorize("hasAuthority('CONSUMER')")
    @RequestMapping(value = "/profile/create")
    public String createTask(@ModelAttribute TaskDto taskDto) {
        Task task = taskConsumerService.createTask(taskDto);
        return "redirect:/task?id=" + task.getId();
    }


}
