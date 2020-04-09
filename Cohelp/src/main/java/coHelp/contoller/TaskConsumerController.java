package coHelp.contoller;

import coHelp.config.details.UserDetailsImpl;
import coHelp.dto.SignUpDto;
import coHelp.dto.TaskDto;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.repository.TaskRepository;
import coHelp.repository.VolunteerRepository;
import coHelp.service.TaskConsumerService;
import coHelp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class TaskConsumerController {

    @Autowired
    TaskConsumerService taskConsumerService;


    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task/done", method = RequestMethod.POST)
    public String acceptTask(@RequestParam(value = "taskId") Long id, Authentication authentication) {
        User user =((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Task task = taskService.getTask(id).get();
        taskConsumerService.acceptTask(task);
        return "redirect:/task?id="+id;
    }

    @RequestMapping(value = "/task/reject", method = RequestMethod.POST)
    public String confirmTask(@RequestParam(value = "taskId") Long id) {
        Task task = taskService.getTask(id).get();
        taskConsumerService.rejectTask(task);
        return "redirect:/task?id="+id;
    }







}
