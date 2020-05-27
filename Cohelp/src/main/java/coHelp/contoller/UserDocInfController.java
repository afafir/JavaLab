package coHelp.contoller;

import coHelp.config.security.details.UserDetailsImpl;
import coHelp.dto.DocumentInformationDto;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDocInfController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/getInfoAboutDoc")
    public ModelAndView getPage(Authentication authentication){
        Long userId = ((UserDetailsImpl)authentication.getPrincipal()).getUser().getId();
        DocumentInformationDto documentInformationDto= userRepository.findInfo(userId).get();
        ModelAndView modelAndView = new ModelAndView("infoDto");
        modelAndView.addObject("docInfo", documentInformationDto);
        return modelAndView;
    }
}
