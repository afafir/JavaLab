package coHelp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {


    @RequestMapping("/")
    public ModelAndView mainPage() {
        return new ModelAndView("index");
    }

}
