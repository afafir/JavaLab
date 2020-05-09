package coHelp.contoller;

import coHelp.exception.ResourceNotFoundException;
import coHelp.model.Visitor;
import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    Visitor visitor;
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
            modelAndView.addObject("addr", visitor.getAddress());
        } else {
            throw new ResourceNotFoundException();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView getTasksPage() {
        ModelAndView modelAndView = new ModelAndView("tasks");
        List<Task> taskList = taskService.getActiveTasks();
        modelAndView.addObject("tasks", taskList);
        return modelAndView;
    }


}
