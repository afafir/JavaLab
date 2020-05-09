package coHelp.contoller.rest;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import coHelp.dto.user.UserDto;
import coHelp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileRestController {

    @Autowired
    ProfileService profileService;

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/rest/profile")
    public ResponseEntity<UserDto> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(profileService.getProfile(userDetails.getUserId()));
    }


}
