package coHelp.contoller;

import coHelp.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VolunteerController {
    @Autowired
    VolunteerRepository volunteerRepository;

    @RequestMapping("/volunteers")
    public String volunteersPage(Model model) {
        model.addAttribute("volunteers", volunteerRepository.findAll());
        return "volunteers";

    }
}
