package coHelp.scope;

import coHelp.model.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class TaskTextPage {

    @Qualifier("task")
    @Autowired
    TaskText taskText;
    @Qualifier("task1")
    @Autowired
    TaskText taskText1;

    @Autowired
    ApplicationContext applicationContext;
    @GetMapping("/scope/{id}")
    ModelAndView getPage(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("forScope");
        Random random = new Random();
        if (random.nextInt(2)<1){
            modelAndView.addObject("text", taskText1.textForPage);
        }else {
            modelAndView.addObject("text", taskText.textForPage);
        }
        return modelAndView;
    }





}
