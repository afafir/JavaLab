package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.TaskGetDto;
import coHelp.exception.ResourceNotFoundException;
import coHelp.model.Visitor;
import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.User;
import coHelp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "404";
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/task", method = RequestMethod.GET, params = {"id"})
    public ModelAndView getPage(@RequestParam(value = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Taskpage");
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            modelAndView.addObject("task", task.get());
        } else {
            throw new ResourceNotFoundException();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView getTasksPage() {
        ModelAndView modelAndView = new ModelAndView("tasks");
        List<TaskGetDto> taskList = taskService.getActiveTasks();
        modelAndView.addObject("tasks", taskList);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('VOLUNTEER')")
    @RequestMapping(value = "/tasks/district", method = RequestMethod.GET)
    public @ResponseBody List<TaskGetDto> getTasksForUserDistrict(Authentication authentication){
        User user = ((coHelp.config.security.details.UserDetailsImpl) authentication.getPrincipal()).getUser();
        return taskService.getTasksForUserDistrict(user);
    }


    @PreAuthorize("hasAuthority('VOLUNTEER')")
    @RequestMapping(value = "/tasks/distance", method = RequestMethod.GET)
    public @ResponseBody List<TaskGetDto> getTasksForDistance(@RequestParam Long km,Authentication authentication){
        User user = ((coHelp.config.security.details.UserDetailsImpl) authentication.getPrincipal()).getUser();
        return taskService.getTaskForDistance(km, user);
    }



}
